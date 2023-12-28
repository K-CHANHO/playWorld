package com.example.playWorld.user;

import com.example.playWorld.token.dto.JwtTokenDTO;
import com.example.playWorld.token.JwtTokenProvider;
import com.example.playWorld.token.entity.RefreshTokenEntity;
import com.example.playWorld.token.repository.RefreshTokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JwtTokenDTO logIn(String username, String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // 로그인 정보 확인
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 토큰 생성
        JwtTokenDTO jwtTokenDTO = jwtTokenProvider.createToken(authentication);

        // Refresh Token DB에 저장
        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.builder().refreshToken(jwtTokenDTO.getRefreshToken()).build();
        RefreshTokenEntity savedRefreshToken = refreshTokenRepository.save(refreshTokenEntity);

        // 저장된 Refresh Token의 Id값 반환
//        jwtTokenDTO.setRefreshTokenId(savedRefreshToken.getTokenId());

        return jwtTokenDTO;
    }

    @Transactional
    public UserDTO signUp(UserDTO userDTO){
        if(userRepository.existsByLoginId(userDTO.getLoginId())){
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setRoles("USER");
        UserEntity savedUser = userRepository.save(UserDTO.toEntity(userDTO));

        return UserDTO.toDTO(savedUser);
    }

    @Transactional
    public JwtTokenDTO loginRefresh(HttpServletRequest request, Authentication authentication){

        String refreshToken = jwtTokenProvider.resolveToken(request);

        if(refreshToken != null && jwtTokenProvider.validateToken(refreshToken)){
            // 토큰 생성
            JwtTokenDTO jwtTokenDTO = jwtTokenProvider.createToken(authentication);

            // Refresh Token DB에 저장
            RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.builder().refreshToken(jwtTokenDTO.getRefreshToken()).build();
            RefreshTokenEntity savedRefreshToken = refreshTokenRepository.save(refreshTokenEntity);

            // 기존 Refresh Token 삭제 ==> TODO : 인덱스 추가해서 update로 변경하기
            refreshTokenRepository.deleteByRefreshToken(refreshToken);

            return jwtTokenDTO;
        }

        return null;
    }
}

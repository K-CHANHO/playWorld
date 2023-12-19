package com.example.playWorld.user;

import com.example.playWorld.security.SimplePasswordEncoder;
import com.example.playWorld.token.JwtTokenDTO;
import com.example.playWorld.token.JwtTokenProvider;
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
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JwtTokenDTO logIn(String username, String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        JwtTokenDTO jwtTokenDTO = jwtTokenProvider.createToken(authentication);

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
}

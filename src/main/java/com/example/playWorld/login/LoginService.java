package com.example.playWorld.login;

import com.example.playWorld.member.MemberDTO;
import com.example.playWorld.token.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LoginService {

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public Map<String, String> login(MemberDTO memberDTO){
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberDTO.getLoginId(), memberDTO.getPasswd());
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String accessToken = jwtTokenProvider.createAccessToken(memberDTO);
        String refreshToken = jwtTokenProvider.createRefreshToken(memberDTO);

        Map<String, String> test = new HashMap<>();
        test.put("accessToken", accessToken);
        test.put("refreshToken", refreshToken);

        return test;

    }
}

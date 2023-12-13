package com.example.playWorld.login;

import com.example.playWorld.member.MemberDTO;
import com.example.playWorld.token.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LoginService {

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    TokenService tokenService;

    public Map<String, String> login(MemberDTO memberDTO){
        log.info("START : 1");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberDTO.getLoginId(), memberDTO.getPasswd());
        log.info("START : 2");
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        log.info("START : 3");
        String accessToken = tokenService.createAccessToken(memberDTO);
        String refreshToken = tokenService.createRefreshToken(memberDTO);

        Map<String, String> test = new HashMap<>();
        test.put("accessToken", accessToken);
        test.put("refreshToken", refreshToken);

        return test;

    }
}

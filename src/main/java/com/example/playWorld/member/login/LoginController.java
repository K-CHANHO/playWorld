package com.example.playWorld.member.login;

import com.example.playWorld.member.MemberDTO;
import com.example.playWorld.member.MemberService;
import com.example.playWorld.token.JwtTokenDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    MemberService memberService;

    @PostMapping("/login")
    public JwtTokenDTO login(LoginRequestDTO loginRequestDTO){
        JwtTokenDTO jwtTokenDTO = memberService.logIn(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());

        log.info("Access Token : {}", jwtTokenDTO.getAccessToken());
        log.info("Refresh Token : {}", jwtTokenDTO.getRefreshToken());

        return jwtTokenDTO;
    }

}

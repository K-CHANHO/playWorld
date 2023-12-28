package com.example.playWorld.user;

import com.example.playWorld.token.dto.JwtTokenDTO;
import com.example.playWorld.token.service.RefreshTokenService;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity login(UserDTO userDTO){

        JwtTokenDTO jwtTokenDTO = userService.logIn(userDTO.getLoginId(), userDTO.getPassword());

        return new ResponseEntity(jwtTokenDTO, HttpStatus.OK);
    }

    @PostMapping("/login/refresh")
    public ResponseEntity loginRefresh(HttpServletRequest request, Authentication authentication){

        userService.loginRefresh(request, authentication);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(UserDTO userDTO){

        try {

            UserDTO savedUserDTO = userService.signUp(userDTO);
            return new ResponseEntity(savedUserDTO, HttpStatus.OK);

        } catch (IllegalArgumentException e){

            return new ResponseEntity(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("loginNow")
    // TODO : 추후에 지우기
    public ResponseEntity loginNow(Principal principal, Authentication authentication){


        return new ResponseEntity(authentication, HttpStatus.OK);
    }




}

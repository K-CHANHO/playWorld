package com.example.playWorld.user;

import com.example.playWorld.token.JwtTokenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public JwtTokenDTO login(UserDTO userDTO){

        return userService.logIn(userDTO.getLoginId(), userDTO.getPassword());
    }

    @PostMapping("/sign-up")
    public UserDTO signUp(UserDTO userDTO){
        return userService.signUp(userDTO);
    }


}

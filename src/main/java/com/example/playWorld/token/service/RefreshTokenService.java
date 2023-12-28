package com.example.playWorld.token.service;

import com.example.playWorld.token.entity.RefreshTokenEntity;
import com.example.playWorld.token.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

}

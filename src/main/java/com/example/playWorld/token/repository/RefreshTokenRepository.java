package com.example.playWorld.token.repository;

import com.example.playWorld.token.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    void deleteByRefreshToken(String refreshToken);
}

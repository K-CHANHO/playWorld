package com.example.playWorld.token.dto;

import com.example.playWorld.token.entity.RefreshTokenEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenDTO {

    private Long tokenId;

    private String refreshToken;


    public static RefreshTokenEntity toEntity(RefreshTokenDTO dto){
        RefreshTokenEntity entity = RefreshTokenEntity.builder()
                .tokenId(dto.getTokenId())
                .refreshToken(dto.getRefreshToken())
                .build();

        return entity;
    }

    public static RefreshTokenDTO toDTO(RefreshTokenEntity entity) {
        RefreshTokenDTO dto = RefreshTokenDTO.builder()
                .tokenId(entity.getTokenId())
                .refreshToken(entity.getRefreshToken())
                .build();

        return dto;
    }
}

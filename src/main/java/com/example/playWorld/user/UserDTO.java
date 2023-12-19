package com.example.playWorld.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String uid;
    private String loginId;
    private String password;
    private String nickname;
    private String roles;

    public static UserDTO toDTO(UserEntity entity) {
        UserDTO dto = UserDTO.builder()
                .uid(entity.getUid())
                .loginId(entity.getLoginId())
                .password(entity.getPassword())
                .nickname(entity.getNickname())
                .roles(entity.getRoles().stream().collect(Collectors.joining(",")))
                .build();

        return dto;
    }

    public static UserEntity toEntity(UserDTO dto) {
        UserEntity entity = UserEntity.builder()
                .uid(dto.getUid())
                .loginId(dto.getLoginId())
                .password(dto.getPassword())
                .nickname(dto.getNickname())
                .roles(Arrays.stream(dto.getRoles().split(",")).toList())
                .build();

        return entity;
    }
}

package com.example.playWorld.member;

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
public class MemberDTO {

    private String uid;
    private String loginId;
    private String passwd;
    private String nickname;
    private String roles;

    public static MemberDTO toDTO(MemberEntity entity) {
        MemberDTO dto = MemberDTO.builder()
                .uid(entity.getUid())
                .loginId(entity.getLoginId())
                .passwd(entity.getPasswd())
                .nickname(entity.getNickname())
                .roles(entity.getRoles().stream().collect(Collectors.joining(",")))
                .build();

        return dto;
    }

    public static MemberEntity toEntity(MemberDTO dto) {
        MemberEntity entity = MemberEntity.builder()
                .uid(dto.getUid())
                .loginId(dto.getLoginId())
                .passwd(dto.getPasswd())
                .nickname(dto.getNickname())
                .roles(Arrays.stream(dto.getRoles().split(",")).toList())
                .build();

        return entity;
    }
}

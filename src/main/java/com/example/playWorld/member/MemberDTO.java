package com.example.playWorld.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private String uid;
    private String loginId;
    private String passwd;
    private String roles;

    public static MemberDTO toDTO(MemberEntity entity) {
        MemberDTO dto = MemberDTO.builder()
                .uid(entity.getUid())
                .loginId(entity.getLoginId())
                .passwd(entity.getPasswd())
                .roles(entity.getRoles())
                .build();

        return dto;
    }

    public static MemberEntity toEntity(MemberDTO dto) {
        MemberEntity entity = MemberEntity.builder()
                .uid(dto.getUid())
                .loginId(dto.getLoginId())
                .passwd(dto.getPasswd())
                .roles(dto.getRoles())
                .build();

        return entity;
    }
}

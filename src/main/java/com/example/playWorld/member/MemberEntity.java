package com.example.playWorld.member;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Table(name = "member")
public class MemberEntity {

    @Id
    @Column
    private String uid;

    @Column
    private String loginId;

    @Column
    private String passwd;

    @Column
    private String nickname;

    @Column
    private String roles;

}

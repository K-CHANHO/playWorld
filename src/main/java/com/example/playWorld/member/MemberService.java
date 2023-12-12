package com.example.playWorld.login;

import com.example.playWorld.member.MemberEntity;
import com.example.playWorld.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberEntity findMember(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
}

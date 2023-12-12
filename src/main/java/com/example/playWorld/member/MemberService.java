package com.example.playWorld.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberEntity findMember(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
}

package com.example.playWorld.security;

import com.example.playWorld.member.MemberEntity;
import com.example.playWorld.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SecurityService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity byLoginId = memberRepository.findByLoginId(username);
        if (byLoginId == null) {
            throw new UsernameNotFoundException("없는 회원입니다.");
        }

        log.info(byLoginId.toString());

        return User.builder()
                .username(byLoginId.getLoginId())
                .password(byLoginId.getPasswd())
                .roles(byLoginId.getRoles())
                .build();
    }
}

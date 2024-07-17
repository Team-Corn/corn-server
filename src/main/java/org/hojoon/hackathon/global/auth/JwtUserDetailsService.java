package org.hojoon.hackathon.global.auth;

import lombok.AllArgsConstructor;
import org.hojoon.hackathon.domain.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new JwtUserDetails(
                memberRepository.findByEmail(email).get()
        );
    }
}
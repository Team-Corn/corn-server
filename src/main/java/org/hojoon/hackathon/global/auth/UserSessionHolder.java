package org.hojoon.hackathon.global.auth;

import org.hojoon.hackathon.domain.member.entity.MemberEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserSessionHolder {
    public MemberEntity current(){
        return ((JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).member();
    }
}

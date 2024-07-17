package org.hojoon.hackathon.domain.member.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hojoon.hackathon.domain.member.entity.MemberEntity;
import org.hojoon.hackathon.domain.member.presentation.dto.MemberLoginDTO;
import org.hojoon.hackathon.domain.member.presentation.dto.MemberRegisterDTO;
import org.hojoon.hackathon.domain.member.presentation.dto.MemberResponseDTO;
import org.hojoon.hackathon.domain.member.service.MemberService;
import org.hojoon.hackathon.global.response.BaseResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/register")
    public BaseResponse<?> postRegister(@Valid @RequestBody MemberRegisterDTO dto) {
        return memberService.postRegister(dto);
    }

    // 로그인
    @PostMapping("/login")
    public BaseResponse<?> postLogin(@Valid @RequestBody MemberLoginDTO dto) {
        return memberService.postLogin(dto);
    }

    // 로그아웃
    @GetMapping("/logout")
    public BaseResponse<?> logout(HttpServletRequest request, HttpServletResponse response) {
        return memberService.logout(request, response);
    }

    // 프로필 확인
    @GetMapping("/{memberId}")
    public @ResponseBody MemberResponseDTO getMember(@PathVariable String memberId) {
        return memberService.getMemberProfile(memberId);
    }

    // 랭킹 조회
    @GetMapping("/rank")
    public List<MemberEntity> rank() {
        return memberService.getMemberRank();
    }

}

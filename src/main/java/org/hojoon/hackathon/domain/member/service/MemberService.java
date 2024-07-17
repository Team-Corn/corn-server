package org.hojoon.hackathon.domain.member.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hojoon.hackathon.domain.member.authority.MemberAccountType;
import org.hojoon.hackathon.domain.member.entity.MemberEntity;
import org.hojoon.hackathon.domain.member.presentation.dto.MemberLoginDTO;
import org.hojoon.hackathon.domain.member.presentation.dto.MemberRegisterDTO;
import org.hojoon.hackathon.domain.member.presentation.dto.MemberResponseDTO;
import org.hojoon.hackathon.domain.member.repository.MemberRepository;
import org.hojoon.hackathon.global.auth.JwtUtils;
import org.hojoon.hackathon.global.exception.CustomErrorCode;
import org.hojoon.hackathon.global.exception.CustomException;
import org.hojoon.hackathon.global.response.BaseResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.hojoon.hackathon.global.exception.CustomErrorCode.MEMBER_NOT_EXIST;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtils jwtUtils;

    // 회원가입 구현
    public BaseResponse<?> postRegister(MemberRegisterDTO dto) {

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new CustomException(CustomErrorCode.PASSWORDS_DO_NOT_MATCH);
        }

        if (memberRepository.existsById(dto.getId())) {
            throw new CustomException(CustomErrorCode.MEMBER_ALREADY_EXIST);
        }

        memberRepository.save(
                MemberEntity.builder()
                        .id(dto.getId())
                        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                        .name(dto.getName())
                        .gender(dto.getGender())
                        .email(dto.getEmail())
                        .birth(dto.getBirth())
                        .authority(MemberAccountType.ROLE_USER)
                        .fileUrl(dto.getFileUrl())
                        .build()
        );

        return BaseResponse.of(
                true,
                "OK",
                "회원가입 성공",
                null
        );
    }

    // 로그인 구현
    public BaseResponse<?> postLogin(MemberLoginDTO dto) {
        MemberEntity member = memberRepository.findById(dto.getId())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_EXIST));

        if (!bCryptPasswordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new CustomException(CustomErrorCode.MEMBER_NOT_CORRECT);
        }

        return BaseResponse.of(
                true,
                "OK",
                "로그인 성공",
                List.of(
                        jwtUtils.generateToken(member)
                )
        );
    }

    // 로그아웃 구현
    public BaseResponse<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return BaseResponse.of(
                true,
                "OK",
                "로그아웃 성공",
                null
        );
    }

    // 프로필 정보 가져오기
    public MemberResponseDTO getMemberProfile(String memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_EXIST));

        return MemberResponseDTO.builder()
                .id(memberEntity.getId())
                .email(memberEntity.getEmail())
                .name(memberEntity.getName())
                .birth(memberEntity.getBirth())
                .gender(memberEntity.getGender())
                .temperature(memberEntity.getTemperature())
                .authority(memberEntity.getAuthority())
                .plogging(memberEntity.getPlogging())
                .fileUrl(memberEntity.getFileUrl())
                .build();
    }

    // 랭킹 기능
    public List<MemberEntity> getMemberRank() {
        List<MemberEntity> memberEntities = memberRepository.findAll();
        memberEntities.sort((o1, o2) -> {
            Float o1Score = o1.getTemperature();
            Float o2Score = o2.getTemperature();
            return (int) -(o1Score - o2Score);
        });
        return memberEntities;
    }


}

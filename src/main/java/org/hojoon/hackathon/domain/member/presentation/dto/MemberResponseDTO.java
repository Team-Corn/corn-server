package org.hojoon.hackathon.domain.member.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hojoon.hackathon.domain.member.authority.MemberAccountType;
import org.hojoon.hackathon.domain.member.entity.MemberEntity;
import org.hojoon.hackathon.domain.member.presentation.enums.Gender;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponseDTO {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String birth;

    @NotBlank
    private Float temperature;

    @NotNull
    private MemberAccountType authority;

    @NotNull
    private Gender gender;

    @NotBlank
    private Integer plogging;

    private String fileUrl;

    public static MemberResponseDTO of (MemberEntity member) {
        return MemberResponseDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .birth(member.getBirth())
                .temperature(member.getTemperature())
                .authority(member.getAuthority())
                .gender(member.getGender())
                .plogging(member.getPlogging())
                .fileUrl(member.getFileUrl())
                .build();
    }
}

package org.hojoon.hackathon.domain.member.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hojoon.hackathon.domain.member.presentation.enums.Gender;

@Getter
public class MemberRegisterDTO {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String birth;

    @NotNull
    private Gender gender;

    private String fileUrl;

}

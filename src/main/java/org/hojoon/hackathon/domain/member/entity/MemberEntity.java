package org.hojoon.hackathon.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hojoon.hackathon.domain.member.authority.MemberAccountType;
import org.hojoon.hackathon.domain.member.presentation.enums.Gender;

@Getter
@Setter
@Entity
@Table(name = "member")
@NoArgsConstructor
public class MemberEntity {
    // 회원 pk
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    // 회원 아이디
    @Column(name = "id", nullable = false)
    private String id;

    // 비밀번호
    @Column(name = "password", nullable = false)
    private String password;

    // 회원 이름
    @Column(name = "name", nullable = false)
    private String name;

    // 이메일
    @Column(name = "email", nullable = false)
    private String email;

    // 생일
    @Column(name = "birth", nullable = false)
    private String birth;

    // 온도
    @Column(name = "temperature", nullable = false)
    private Float temperature;

    // 플로깅 한 횟수
    @Column(name = "plogging", nullable = false)
    private Integer plogging;

    // 권한
    @Column(name = "authority", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberAccountType authority;

    // 성별
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    // 프로필 사진
    private String fileUrl;

    @Builder
    public MemberEntity(
        Long idx,
        String id,
        String password,
        String name,
        String email,
        String birth,
        MemberAccountType authority,
        Gender gender,
        String fileUrl
    ) {
        this.idx = idx;
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.temperature = 36.5F;
        this.authority = authority;
        this.gender = gender;
        this.plogging = 0;
        this.fileUrl = fileUrl;
    }
}

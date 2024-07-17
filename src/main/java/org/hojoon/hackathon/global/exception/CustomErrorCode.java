package org.hojoon.hackathon.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
    // 서버 오류
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S-1", "서버에서 오류가 발생했습니다."),

    // 멤버 관련 오류
    MEMBER_ALREADY_EXIST(HttpStatus.CONFLICT, "M-1", "멤버가 이미 존재합니다."),
    MEMBER_NOT_EXIST(HttpStatus.NOT_FOUND, "M-2", "멤버가 존재하지 않습니다."),
    MEMBER_NOT_CORRECT(HttpStatus.BAD_REQUEST, "M-3", "멤버 정보가 일치하지 않습니다."),
    PASSWORDS_DO_NOT_MATCH(HttpStatus.BAD_REQUEST, "M-4", "비밀번호가 일치하지 않습니다."),
    MEMBER_NOT_AUTHORITY(HttpStatus.FORBIDDEN, "M-5", "멤버의 권한이 없습니다."),

    // JWT 관련 오류
    JWT_WAS_EXPIRED(HttpStatus.FORBIDDEN, "J-1", "토큰이 만료되었습니다."),

    // 게시물 관련 오류
    BOARD_NOT_EXIST(HttpStatus.NOT_FOUND, "B-1", "게시물이 존재하지 않습니다."),


    // 캠페인 관련 오류
    CAMPAIGN_NOT_EXIST(HttpStatus.NOT_FOUND, "C-1", "캠페인이 존재하지 않습니다.");

    private final HttpStatus code;
    private final String status;
    private final String message;
}

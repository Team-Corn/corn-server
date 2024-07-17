package org.hojoon.hackathon.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private final CustomErrorCode errorCode;

}
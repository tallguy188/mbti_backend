package com.mbti.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
public enum ErrorCode {

    // 409:conflict: 중복 데이터 존재시

    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "이미 존재합니다."),
    INVALID_PASSWORD(HttpStatus.NOT_FOUND,"invalid"),

    NOT_FOUND(HttpStatus.NOT_FOUND,"not found");
    private final HttpStatus httpStatus;
    private final String message;


}



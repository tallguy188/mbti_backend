package com.mbti.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinLoginException extends RuntimeException{

    ErrorCode errorCode;
    private String message;

    @Override
    public String toString() {
        if(message == null) {
            return errorCode.getMessage();
        }

        return String.format("%s. %s",errorCode.getMessage(),message);

    }

}

package com.mbti.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

//모든 response는 현재 response 객체로 감싸서 return
//제너릭생성시 t는 객체 생성시 해당 타입으로 변경됨.
public class Response<T> {

    private String resultCode;
    private T result;



    public static Response<Void>error(String resultCode) {
        return new Response<>(resultCode,null);
    }

    // success
    public static <T> Response<T>success(T result) {
        return new Response<>("success",result);

    }


}

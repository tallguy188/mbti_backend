package com.mbti.presentation.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class userRequestDto{
        private Integer id;  // idx값, user가 입력하는 id는 nick
        private String nick;   // 이게 유저가 입력하는 아이디
        private String pw;
        private String mbti;

    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public  static class registerResponseDto{
       private Integer id;

       private String nick;

       private String mbti;



    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class userLoginResponseDto {   //토큰의 인코딩 값을 반환
        private String token;  // 토큰안에는 유저의 데이터가 암호화되어 저장
        private String mbti;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class userLogoutRequestDto {
        private String nick;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class userLoginListResponseDto {
        private String nick;
        private String mbti;

    }


}

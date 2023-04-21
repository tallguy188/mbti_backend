package com.mbti.presentation.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BoardDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class BoardRequestDto{

        private String title;

        private String nick;   // 게시물작성자

        private String content;

        private String regdate;

        private String mbti;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class BoardResponseDto {
        private Integer id;
    }


}

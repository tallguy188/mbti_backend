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
    public static class boardSaveRequestDto{
        private String title;
        private String nick;   // 게시물작성자
        private String content;
        private String regdate;
        private String mbti;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class boardSaveResponseDto {
        private Integer id;
    }


    @Getter
    @AllArgsConstructor
    @Builder
    public static class boardDetailResponseDto {
        private Integer id;
        private String title;
        private String content;
        private String date;
        private String mbti;
        private String writer;  //nick값을 넣어주면 될듯

    }
}

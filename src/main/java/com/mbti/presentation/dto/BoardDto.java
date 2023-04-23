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


//    @Getter
//    @AllArgsConstructor
//    @Builder
//    public static class boardDeleteRequestDto {
//        private Integer id;
//    }
}

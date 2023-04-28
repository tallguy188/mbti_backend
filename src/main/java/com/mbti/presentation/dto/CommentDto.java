package com.mbti.presentation.dto;


import com.mbti.domain.entity.Board;
import com.mbti.domain.entity.User;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CommentDto {


    @Getter
    @Setter
    @AllArgsConstructor
    @Builder

    public static class commentSaveRequestDto {


        private String content;
        private String regdate;
        private String writer;
        private Optional<User> user;
        private Board board;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class commentSaveResponseDto{
        private Integer id;     //commentid
        private String writer;
        private Integer articleid;  // 댓글이 저장된 게시물의 id를 넘겨줌
    }


    @Getter
    @AllArgsConstructor
    @Builder
    public static class commentDetailResponseDto {
        private Integer id;
        private String content;
        private String regdate;
        private String writer;
    }


    @Getter
    @AllArgsConstructor
    @Builder
    public static class commentUpdateRequestDto{
        private String content;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class commentUpdateResponseDto{
        private Integer commentid;
        private String content;
    }



}

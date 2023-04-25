package com.mbti.presentation.dto;


import com.mbti.domain.entity.Board;
import com.mbti.domain.entity.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CommentDto {


    @Getter
    @AllArgsConstructor
    @Builder
    public static class CommentSaveRequestDto {


        private String content;
        private String regdate;
        private String writer;
        private User user;
        private Board board;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class CommentSaveResponseDto{
        private Integer id;
        private String writer;
        private String articleid;  // 댓글이 저장된 게시물의 id를 넘겨줌
    }

}

package com.mbti.presentation.dto;


import com.mbti.domain.entity.User;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class McommentDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class requestDto {

        private String writer;
        private String mcommentContent;

    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class mcommentDetailResponseDto{

        private Integer commenId;
        private String writer;
        private String mcommentContent;
    }

}

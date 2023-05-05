package com.mbti.presentation.dto;


import com.mbti.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ChatDto {


    @Getter
    @AllArgsConstructor
    @Builder
    public static class chatMessageDto {
        private String chatId;
        private List<String> chatUser;   // 사용자 정보를 Nick으로 받아옴.
        private String messageContent;
    }
}

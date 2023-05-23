package com.mbti.presentation.controller;


import com.mbti.application.ChatService;
import com.mbti.application.UserService;
import com.mbti.domain.entity.Chatroom;
import com.mbti.domain.entity.User;
import com.mbti.presentation.dto.ChatDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Tag(name="채팅관리",description = "채팅 관리 api입니다.")
public class ChatController {
    private final ChatService chatService;

    private final UserService userService;



    // 인증된 사용자만 접근할 수 있도록 principle 사용
    @Operation(summary = "채팅 매칭", description = "채팅방 생성 메소드입니다.")
    @PostMapping("/createChatRoom")
    public ResponseEntity<String> createChatRoom (@RequestBody ChatDto.chatUserDto chatUserDto, Principal principal) {
        String loggedInUsername = principal.getName();
        User loggedInUser = userService.getUserByUsername(loggedInUsername);

        // 선택한 회원 정보 가져오기
        User selectUser = userService.getUserByUsername(chatUserDto.getUserNick());

        Chatroom chatroom = chatService.createChatRoom(loggedInUser,selectUser);
        chatService.saveChat(chatroom);

        return ResponseEntity.ok("1대1 채팅방이 생성되었습니다.");
    }

}

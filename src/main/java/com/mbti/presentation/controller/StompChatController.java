package com.mbti.presentation.controller;

import com.mbti.presentation.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template;   // 특정 broker로 메세지를 전달

    // client가 SEND 할 수 있는 경로
    // stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨.
    // "/pub/chat/enter"
    @MessageMapping(value = "/chat/enter")  // "/chat/enter"로 들어오는 메시지를 처리하는 역할, 즉 클라이언트가
    // "/chat/enter"로 메시지를 보내면 메서드가 호출
    public void enter(ChatDto.chatMessageDto message) {
        message.setMessage(message.getWriter() + "님이 채팅방에 입장하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(),message);
        // 위에서 수정된 메시지 객체를 채팅방을 구독하고 있는 모든 클라이언트에게 전송
    }  // 즉 채팅방에 입장하면 구독하고 있는 모든 클라이언트에게 입장을 알림
    @MessageMapping(value = "/chat/message")
    public void message(ChatDto.chatMessageDto message) {
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(),message);
        // 구독하고 있는 모든 클라이언트에게 메세지를 전송
    }
}
// client에서는 해당 주소를 SUBSCRIBE하고 있다가 메세지를 전달되면 화면에 출력한다.
// 기존에 chathandler의 역할을 대신 해주므로 핸들러는 따로 필요없다.
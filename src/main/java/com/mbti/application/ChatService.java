package com.mbti.application;

import com.mbti.common.exception.UserNotFoundException;
import com.mbti.domain.entity.Chatroom;
import com.mbti.domain.entity.User;
import com.mbti.domain.repository.UserRepository;
import com.mbti.domain.repository.WebSocketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final UserRepository userRepository;
    private final WebSocketRepository webSocketRepository;

    // 채팅방 생성
    public Chatroom createChatRoom(User loggedInUser,User selectUser) {

        Chatroom chatroom = Chatroom.builder()
                .chatUser(Arrays.asList(loggedInUser,selectUser))
                .build();
        return chatroom;
    }

    // 송신자 및 수신자 유효성 검사
    public void validateUser(String senderNick, String receiverNick) {
        userRepository.findByUserNick(senderNick).orElseThrow(() -> new UserNotFoundException("존재하지 않는 유저입니다."));

        userRepository.findByUserNick(receiverNick).orElseThrow(() -> new UserNotFoundException("존재하지 않는 유저입니다."));

    }

    // 메시지 수신 및 송신 처리
    public void processMessage(String senderNick, String receiverNick, String content) {
        User sender = userRepository.findByUserNick(senderNick).orElseThrow(() -> new UserNotFoundException("발신자가 불분명합니다."));

        User receiver = userRepository.findByUserNick(receiverNick).orElseThrow(() ->  new UserNotFoundException("수신자가 불분명합니다."));


        WebSocketSession sendersession = getWebSocketSessionByUserId(sender.getUserId());

        if(sendersession != null && sendersession.isOpen()) {
            try {
                sendersession.sendMessage(new TextMessage(content));
            }catch (IOException e) {
                System.out.println("메시지 전송 오류" + e.getMessage());
            }
        }

        WebSocketSession receiversession = getWebSocketSessionByUserId(receiver.getUserId());
        if(receiversession !=null && receiversession.isOpen()) {
            try{
                receiversession.sendMessage(new TextMessage(content));
            }catch(IOException e) {
                System.out.println("메시지 전송 오류" + e.getMessage());
            }
        }

    }

    private WebSocketSession getWebSocketSessionByUserId(Integer userId) {

        return webSocketRepository.getUserSession(userId);
    }
}

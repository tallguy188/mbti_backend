package com.mbti.application;

import com.mbti.common.exception.UserNotFoundException;
import com.mbti.domain.entity.Chatroom;
import com.mbti.domain.entity.User;
import com.mbti.domain.repository.ChatRepository;
import com.mbti.domain.repository.UserRepository;
import com.mbti.domain.repository.WebSocketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final WebSocketRepository webSocketRepository;

    // chat repo에 chat 저장
    public void saveChat(Chatroom chatroom) {
        chatRepository.save(chatroom);
    }

    // chatuser 가져오기
    public List<User> getChatUser(Integer chatId) {
        return chatRepository.getChatByChatUser(chatId);
    }
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

    // 수신한 메시지 처리
    public void processMessage(String senderNick, String receiverNick, String content) {
        User sender = userRepository.findByUserNick(senderNick).orElseThrow(() -> new UserNotFoundException("발신자가 불분명합니다."));

        User receiver = userRepository.findByUserNick(receiverNick).orElseThrow(() ->  new UserNotFoundException("수신자가 불분명합니다."));

        WebSocketSession session = getWebSocketSessionByUserId(receiver.getUserId());
        if(session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(content));
            }catch (IOException e) {
                System.out.println("메시지 전송 오류" + e.getMessage());
            }

        }
    }

    private WebSocketSession getWebSocketSessionByUserId(Integer userId) {

        return webSocketRepository.getUserSession(userId);
    }
}

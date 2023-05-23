package com.mbti.application;

import com.mbti.domain.entity.Chatroom;
import com.mbti.domain.entity.User;
import com.mbti.domain.repository.ChatRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;


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




}

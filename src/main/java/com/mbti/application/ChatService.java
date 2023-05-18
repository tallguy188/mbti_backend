package com.mbti.application;

import com.mbti.domain.entity.Chatroom;
import com.mbti.domain.entity.User;
import com.mbti.domain.repository.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
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




}

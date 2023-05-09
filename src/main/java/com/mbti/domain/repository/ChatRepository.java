package com.mbti.domain.repository;

import com.mbti.domain.entity.Chat;
import com.mbti.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Integer> {
    List<User> getChatByChatUser(Integer chatId);
}

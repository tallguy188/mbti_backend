package com.mbti.domain.repository;


import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class WebSocketRepository {

    private Map<Integer, WebSocketSession> userSessions = new ConcurrentHashMap<>();

    public void addUserSession(Integer userId, WebSocketSession session) {
        userSessions.put(userId, session);
    }

    public void removeUserSession(Integer userId) {
        userSessions.remove(userId);
    }

    public WebSocketSession getUserSession(Integer userId) {
        return userSessions.get(userId);
    }
}

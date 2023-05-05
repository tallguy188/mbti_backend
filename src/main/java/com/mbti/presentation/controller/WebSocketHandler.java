package com.mbti.presentation.controller;


import com.mbti.application.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    private final ChatService chatService;


    // 채팅방 목록
    private Map<Integer, ArrayList<WebSocketSession>>ChatList = new ConcurrentHashMap<Integer,ArrayList<WebSocketSession>>();





}

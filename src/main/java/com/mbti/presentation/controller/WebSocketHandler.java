package com.mbti.presentation.controller;


import com.mbti.application.ChatService;
import com.mbti.application.UserService;
import com.mbti.domain.entity.Chatroom;
import com.mbti.domain.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Tag(name="WebSocket API", description = "WebSocket API 문서입니다.")
public class WebSocketHandler extends TextWebSocketHandler {
    private final ChatService chatService;
    private final UserService userService;



    // 채팅방 목록
    private Map<Integer, ArrayList<WebSocketSession>>chatList = new ConcurrentHashMap<Integer,ArrayList<WebSocketSession>>();
    private Map<WebSocketSession,Integer> sessionList = new ConcurrentHashMap<WebSocketSession,Integer>();


    // 웹 소켓 연결 성공, 새로운 클라이언트 접속했을 때 실행
    // 채팅방 생성

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        // 클라이언트가 보낸 채팅방 입장 요청을 처리
         if(session.getAttributes().containsKey("chatId") && session.getAttributes().containsKey("userIds")){
             Integer chatId = (Integer) session.getAttributes().get("chatId");
             List<Integer> userIds = (List<Integer>) session.getAttributes().get("userIds");

             // chat 텐티티 생성, 저장
             Chatroom chatroom = Chatroom.builder()
                     .chatId(chatId)
                     .chatUser(userIds.stream().map(id->userService.getUserById(id)).collect(Collectors.toList()))
                     .build();

             chatService.saveChat(chatroom);  // 채팅저장(내용은 저장x)

             // 해당 채팅방에 websocketsession추가
             chatList.computeIfAbsent(chatId, k-> new ArrayList<>()).add(session);
             sessionList.put(session,chatId);

             // 웹소켓 세션의 attribute에 사용자 정보 저장
             List<User> chatUser  = chatService.getChatUser(chatId);
         }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
        // 클라이언트에서 메시지를 보내면 호출됨 
        String chatRoomId = (String) session.getAttributes().get("chatRoomId");
        List<WebSocketSession> sessions = chatList.get(chatRoomId);

        // sessions리스트 안의 웹소켓 세션들을 순회
        for(WebSocketSession webSocketSession : sessions) {
            if(webSocketSession.isOpen()) {
                webSocketSession.sendMessage(new TextMessage(message.getPayload()));
            }
        }

    }


    // 웹 소켓 연결 종료시
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션리스트에서 세션을 가져와서 없애줌.
        Integer chatRoomId  = sessionList.get(session);
        sessionList.remove(session);
        // 웹소켓세션리스트에서 세션을 없애줌.
        List<WebSocketSession> sessions = chatList.get(chatRoomId);
        sessions.remove(session);
    }


    // 전송 오류 시
    @Override
    public void handleTransportError(WebSocketSession session , Throwable exception) throws Exception{
        // 세션이 열려있으면 에러코드와 함께 닫음.
        if(session.isOpen()) {
            session.close(CloseStatus.SERVER_ERROR);
        }

    }

}

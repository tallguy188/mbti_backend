package com.mbti.presentation.controller;


import com.mbti.application.ChatService;
import com.mbti.application.UserService;
import com.mbti.domain.entity.Chatroom;
import com.mbti.domain.entity.User;
import com.mbti.domain.repository.WebSocketRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.Collections;
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
    private final WebSocketRepository webSocketRepository;
    // 채팅방 목록
    private Map<String, ArrayList<WebSocketSession>>chatList = new ConcurrentHashMap<String,ArrayList<WebSocketSession>>();
    private Map<WebSocketSession,String> sessionList = new ConcurrentHashMap<WebSocketSession,String>();

    // 웹 소켓 연결 성공, 새로운 클라이언트 접속했을 때 실행
    // 채팅방 생성
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        // 클라이언트가 보낸 채팅방 입장 요청을 처리
         if(session.getAttributes().containsKey("userIds")){
             List<Integer> userIds = (List<Integer>) session.getAttributes().get("userIds");

             String chatRoomId = generateChatRoomId(userIds);

             // 해당 채팅방에 websocketsession추가
             chatList.computeIfAbsent(chatRoomId, k-> new ArrayList<>()).add(session);
             sessionList.put(session,chatRoomId);

             // 웹소켓 세션을 userIds의 각 사용자에게 연결
             for(Integer userId:userIds) {
                 webSocketRepository.addUserSession(userId,session);
             }

//              웹소켓 세션의 attribute에 사용자 정보 저장
//             List<User> chatUser  = chatService.getChatUser(chatId);
         }
    }
    private String generateChatRoomId(List<Integer> userIds) {
        Collections.sort(userIds);
        return String.join("-", userIds.stream().map(Object::toString).collect(Collectors.toList()));
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
        String chatRoomId  = sessionList.get(session);
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

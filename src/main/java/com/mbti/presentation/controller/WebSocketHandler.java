package com.mbti.presentation.controller;


import com.mbti.application.ChatService;
import com.mbti.application.UserService;
import com.mbti.domain.entity.Chat;
import com.mbti.domain.entity.User;
import com.mbti.domain.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.http.WebSocket;
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
    private final UserRepository userRepository;


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
             Chat chat = Chat.builder()
                     .chatId(chatId)
                     .chatUser(userIds.stream().map(id->userService.getUserById(id)).collect(Collectors.toList()))
                     .build();

             chatService.saveChat(chat);  // 채팅저장(내용은 저장x)

             // 해당 채팅방에 websocketsession추가
             chatList.computeIfAbsent(chatId, k-> new ArrayList<>()).add(session);
             sessionList.put(session,chatId);

             // 웹소켓 세션의 attribute에 사용자 정보 저장
             List<User> chatUser  = chatService.getChatUSer(chatId);
         }
    }

    // 웹 소켓 연결 종료시
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println(session.getId());
        //세션이 존재한다면
        if(sessionList.get(session)!=null) {
            // 해당 세션의 방 번호를 가져와서 방을 찾고, 해당 세션을 지운다.
            chatList.get(sessionList.get(session)).remove(session);
            sessionList.remove(session);
        }
    }


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 클라이언트에서 메시지를 보내면 호출됨 
        String chatRommId = (String) session.getAttributes().get("chatRoomId");
        List<WebSocketSession> sessions = chatList.get(chatRommId);

        // sessions리스트 안의 웹소켓 세션들을 순회
        for(WebSocketSession webSocketSession : sessions) {


        }

    }




}

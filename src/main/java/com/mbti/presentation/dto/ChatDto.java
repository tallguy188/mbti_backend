//package com.mbti.presentation.dto;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.web.socket.WebSocketSession;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@AllArgsConstructor
//public class ChatDto {
//
//
//    @Getter
//    @AllArgsConstructor
//    @Builder
//    public static class chatMessageDto {
//        private String roomId;
//        private String writer;
//        private String message;
//    }
//
//    @Getter
//    @AllArgsConstructor
//    @Builder
//    public static class chatRoomDto {
//        private String roomId;
//        private Set<WebSocketSession> sessions = new HashSet<>();  //spring에서 websocket connection이 맺어진 세션
//
//    }
//
//
//
//}

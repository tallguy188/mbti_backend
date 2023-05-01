package com.mbti.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker   //웹소켓서버 사용설정
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/chatting")
                .setAllowedOrigins("http://localhost:9991")
                .withSockJS(); //핸드셰이크 경로 생성


    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.setApplicationDestinationPrefixes("/pub");   // client에서 send요청을 처리
        registry.enableSimpleBroker("/sub");  //해당 경로로 simplebroker를 등록, 해당하는 경로를 SUBSCRIBE하는 client에게 메시지를 전달하는 작업 수행


    }


}

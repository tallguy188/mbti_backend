package com.mbti.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // /example은 Websocket 또는 SockJS Client가 웹소켓 핸드셰이크 커넥션을 생성할 경로
        registry.addEndpoint("/stomp/chat")
                .setAllowedOrigins("http://localhost:9991")
                .withSockJS();

    }


    // 어플리케이션 내부에서 사용할 path를 지정할 수 있음
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){

        // /test경로로 시작하는 STOMP 메세지의 "destination"헤더는 @controller객체의 @MessageMapping 메서드로 라우팅됨.
        registry.setApplicationDestinationPrefixes("/pub");  // client에서 Send요청을 처리
        registry.enableSimpleBroker("/sub"); // 해당 경로로 simplebroker를 등록,
        // simplebroker는 해당하는 경로를 subscribe하는 client에게 메세지를 전달하는 간단한 작업 수행
    }

}
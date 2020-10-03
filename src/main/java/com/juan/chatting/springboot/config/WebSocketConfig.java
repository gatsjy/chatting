package com.juan.chatting.springboot.config;

import com.juan.chatting.springboot.handler.ChatHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author Gatsjy
 * @since 2020-10-03
 */
@Profile("!stomp")
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChatHandler chatHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 해당 endpoint로 handshake가 이루어진다.
        registry.addHandler(chatHandler, "/ws/chat").setAllowedOrigins("*").withSockJS();
    }
}

package com.juan.chatting.springboot.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juan.chatting.springboot.domain.chat.ChatMessage;
import com.juan.chatting.springboot.domain.chat.ChatRoom;
import com.juan.chatting.springboot.domain.chat.ChatRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author Gatsjy
 * @since 2020-10-03
 */
@Slf4j
@Profile("!stomp")
@Component
public class ChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatHandler(ObjectMapper objectMapper, ChatRoomRepository chatRoomRepository){
        this.objectMapper = objectMapper;
        this.chatRoomRepository = chatRoomRepository;
    }

    // session에서 메세지를 수신했을 때 실행된다.
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String payload = message.getPayload();
        log.info("payload : {}", payload);

        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom chatRoom = chatRoomRepository.getChatRoom(chatMessage.getChatRoomId());

        log.info("session : {}", session);
        log.info("message : {}", message);
        log.info("chatMessage : {}", chatMessage);
        log.info("objectMapper : {}", objectMapper);
        chatRoom.handleMessage(session, chatMessage, objectMapper);
    }

    // connection이 맺어진 후 실행된다.
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}

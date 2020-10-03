package com.juan.chatting.springboot.domain.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import sun.misc.MessageUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Gatsjy
 * @since 2020-10-03
 */
@Getter
public class ChatRoom {
    // 채팅방은 id, name, sessions로 구성된다. WebSocketSession은 spring에서 WebSocket Connection이 맺어진 세션을 가리킨다.
    // 편하게 고 수준 socket이라고 생각하자. 해당 session을 통해서 메세지를 보낼 수 있다.
    private String id;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    // 채팅방 생성
    public static ChatRoom create(@NonNull String name){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.id = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }

    public void handleMessage(WebSocketSession session, ChatMessage chatMessage, ObjectMapper objectMapper){
        if(chatMessage.getType() == MessageType.JOIN){
            join(session);
            chatMessage.setMessage(chatMessage.getWriter() + "님이 입장했습니다.");
        }

        send(chatMessage, objectMapper);
    }

    private <T> void send(T messageObject, ObjectMapper objectMapper) {
        try {
            TextMessage message = new TextMessage(objectMapper.writeValueAsString(messageObject));
            sessions.parallelStream().forEach(session -> {
                try {
                    session.sendMessage(message);
                } catch (Exception e) {
                    synchronized (sessions){
                        sessions.remove(session);
                    }
                }
            });
        }catch (JsonProcessingException e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void join(WebSocketSession session) {
        sessions.add(session);
    }

}

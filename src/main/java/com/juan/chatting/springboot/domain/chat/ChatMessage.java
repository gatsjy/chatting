package com.juan.chatting.springboot.domain.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Gatsjy
 * @since 2020-10-03
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String chatRoomId;
    private String writer;
    private String message;
    private MessageType type;

    @Override
    public String toString() {
        return "ChatMessage{" +
                "chatRoomId='" + chatRoomId + '\'' +
                ", writer='" + writer + '\'' +
                ", message='" + message + '\'' +
                ", type=" + type +
                '}';
    }
}

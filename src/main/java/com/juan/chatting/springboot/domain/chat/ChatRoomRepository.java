package com.juan.chatting.springboot.domain.chat;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Gatsjy
 * @since 2020-10-03
 */
@Repository
public class ChatRoomRepository {
    private final Map<String, ChatRoom> chatRoomMap;

    private final Collection<ChatRoom> chatRooms;

    public ChatRoomRepository(){
        // 테스트를 위한 용도로 UUID로 채팅방 id를 지정하여, 3개의 채팅방을 생성해두었다.
        chatRoomMap = Collections.unmodifiableMap(
                Stream.of(ChatRoom.create("1번방"),
                          ChatRoom.create("2번방"),
                          ChatRoom.create("3번방"))
                .collect(Collectors.toMap(ChatRoom::getId, Function.identity()))); // 이거에 대한 해석을 해야한다..

        chatRooms = Collections.unmodifiableCollection(chatRoomMap.values());

    }

    public ChatRoom getChatRoom(String id){
        return chatRoomMap.get(id);
    }

    public Collection<ChatRoom> getChatRooms(){
        return chatRoomMap.values();
    }
}

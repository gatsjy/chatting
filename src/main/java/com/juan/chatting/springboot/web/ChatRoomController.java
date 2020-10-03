package com.juan.chatting.springboot.web;

import com.juan.chatting.springboot.domain.chat.ChatRoom;
import com.juan.chatting.springboot.domain.chat.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Gatsjy
 * @since 2020-10-03
 */
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    // AtomicInteger란 원자성을 보장하는 Integer를 의미한다.
    // 멀티쓰레드환경에서 동기화 문제를 별도의 synchronized 키워드 없이 해결하기 위해서 고안된 방법이다.
    // 현재 쓰레드에 저장된 값과 메싱ㄴ 메모리에 저장된 값을 비교하여 일치하는 경우 새로운 값으로 교체하고, 일치 하지 않는 다면 실패하고 재시도를 한다.
    private final AtomicInteger seq = new AtomicInteger(0);

    @Autowired
    public ChatRoomController(ChatRoomRepository chatRoomRepository){
        this.chatRoomRepository = chatRoomRepository;
    }

    @GetMapping("/rooms")
    public String rooms(Model model){
        model.addAttribute("rooms", chatRoomRepository.getChatRooms());
        return "/chat/room-list";
    }

    @GetMapping("/rooms/{id}")
    public String room(@PathVariable String id, Model model){
        ChatRoom room = chatRoomRepository.getChatRoom(id);
        model.addAttribute("room", room);
        model.addAttribute("member", "member" + seq.incrementAndGet());

        return "/chat/room";
    }
}

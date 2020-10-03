package com.juan.chatting.springboot.web;

import com.juan.chatting.springboot.service.person.UserService;
import com.juan.chatting.springboot.service.posts.PostsService;
import com.juan.chatting.springboot.web.dto.PostsUpdateRequestDto;
import com.juan.chatting.springboot.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gatsjy
 * @since 2020-10-03
 */
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/v1/userUpgrade/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto){
        return userService.update(id, requestDto);
    }
}

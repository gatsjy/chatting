package com.juan.chatting.springboot.config.auth.dto;

import com.juan.chatting.springboot.domain.user.Role;
import com.juan.chatting.springboot.domain.user.User;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.io.Serializable;

@Getter
// @Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;
    private String rolekey;
    private Long id;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.rolekey = user.getRoleKey();
        this.id = user.getId();
    }
}

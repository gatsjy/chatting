package com.juan.chatting.springboot.web.dto;

import com.juan.chatting.springboot.domain.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Gatsjy
 * @since 2020-10-03
 */
@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {

    private Role role;

    @Builder
    public UserUpdateRequestDto(Role role)
    {
        this.role = role;
    }
}

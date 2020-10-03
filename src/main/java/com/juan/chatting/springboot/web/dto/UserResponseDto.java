package com.juan.chatting.springboot.web.dto;

import com.juan.chatting.springboot.domain.posts.Posts;
import com.juan.chatting.springboot.domain.user.Role;
import com.juan.chatting.springboot.domain.user.User;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

/**
 * @author Gatsjy
 * @since 2020-10-03
 */
@Getter
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String picture;
    private Role role;
    private int age;
    private String hobby;
    private String bloodType;
    private String address;
    private LocalDate birthday;
    private String job;
    private String phoneNumber;

    public UserResponseDto(User entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.picture = entity.getPicture();
        this.role = entity.getRole();
        this.age = entity.getAge();
        this.hobby = entity.getHobby();
        this.bloodType = entity.getBloodType();
        this.address = entity.getAddress();
        this.birthday = entity.getBirthday();
        this.job = entity.getJob();
        this.phoneNumber = entity.getPhoneNumber();
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}

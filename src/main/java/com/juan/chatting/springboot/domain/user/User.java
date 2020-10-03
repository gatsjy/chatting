package com.juan.chatting.springboot.domain.user;


import com.juan.chatting.springboot.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter @Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private int age;

    private String hobby;

    private String bloodType;

    private String address;

    private LocalDate birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @OneToOne(cascade = {CascadeType.ALL})
    private Block block;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User(String name, String email, String picture, Role role, int age, String bloodType){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.age = age;
        this.bloodType = bloodType;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(picture, user.picture) &&
                role == user.role &&
                Objects.equals(hobby, user.hobby) &&
                Objects.equals(bloodType, user.bloodType) &&
                Objects.equals(address, user.address) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(job, user.job) &&
                Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, picture, role, age, hobby, bloodType, address, birthday, job, phoneNumber);
    }

    public void update(Role role){
        this.role = role;
    }
}

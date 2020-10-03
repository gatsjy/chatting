package com.juan.chatting.springboot.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gatsjy
 * @since 2020-10-03
 */
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void crud(){
        User user = new User("한주안", "gkswndks123", null, Role.GUEST);
        user.setBloodType("A");

        userRepository.save(user);

        List<User> people = userRepository.findAll();

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("한주안");
        assertThat(people.get(0).getRole()).isEqualTo(Role.GUEST);
        assertThat(people.get(0).getBloodType()).isEqualTo("A");
    }
}
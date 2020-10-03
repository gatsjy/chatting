package com.juan.chatting.springboot.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    void hashCodeAndEquals(){
        User user1 = new User("한주안", "gkswndks123", null, Role.GUEST);
        User user2 = new User("한주안", "gkswndks123", null, Role.GUEST);

        System.out.println(user1.equals(user2));
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());

        Map<User, String> map = new HashMap<>();
        map.put(user1, user1.getEmail());
        map.put(user2, user2.getEmail());

        System.out.println(map);
        System.out.println(map.get(user2));
    }
}

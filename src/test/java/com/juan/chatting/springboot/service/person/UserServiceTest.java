package com.juan.chatting.springboot.service.person;

import com.juan.chatting.springboot.domain.user.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Gatsjy
 * @since 2020-10-03
 */
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlockRepository blockRepository;

    @Test
    void getPeopleExcludesBlocks(){
        givenPeople();

        List<User> result = userService.getPeopleExcludeBlocks();

//        System.out.println(result);
        result.forEach(System.out::println);
    }

    @Test
    void cascadeTest(){
        givenPeople();

        List<User> result = userRepository.findAll();
        result.forEach(System.out::println);

        User user = result.get(3);
        user.getBlock().setStartDate(LocalDate.now());
        user.getBlock().setEndDate(LocalDate.now());

        userRepository.save(user);
        userRepository.findAll().forEach(System.out::println);

        //userRepository.delete(user);
        //userRepository.findAll().forEach(System.out::println);
        //blockRepository.findAll().forEach(System.out::println);

        user.setBlock(null);
        userRepository.save(user);
        userRepository.findAll().forEach(System.out::println);
    }

    private void givenPeople() {
        givenUser("juan", "gkswndks123", "", Role.GUEST,10, "A");
        givenUser("martin", "martin123", "", Role.GUEST,9, "B");
        givenUser("dennis", "martin123", "", Role.GUEST, 7, "O");
        givenBlockPerson("david", "martin123", "", Role.GUEST, 11, "AB");
    }

    private void givenUser(String name, String email, String picture, Role role, int age, String bloodType) {
        userRepository.save(new User( name, email, picture, role, age, bloodType));
    }

    private void givenBlockPerson(String name, String email, String picture, Role role, int age, String bloodType){
        User blockPerson = new User(name, email, picture, role, age, bloodType);
        //blockPerson.setBlock(givenBlock(name));
        //user을 저장하기전에 block을 먼저 저장함
        blockPerson.setBlock(new Block(name));

        userRepository.save(blockPerson);
    }
}
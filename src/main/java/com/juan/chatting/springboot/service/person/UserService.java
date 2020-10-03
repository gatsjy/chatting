package com.juan.chatting.springboot.service.person;

import com.juan.chatting.springboot.domain.posts.Posts;
import com.juan.chatting.springboot.domain.user.*;
import com.juan.chatting.springboot.web.dto.PostsResponseDto;
import com.juan.chatting.springboot.web.dto.PostsUpdateRequestDto;
import com.juan.chatting.springboot.web.dto.UserResponseDto;
import com.juan.chatting.springboot.web.dto.UserUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gatsjy
 * @since 2020-10-03
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlockRepository blockRepository;

    public List<User> getPeopleExcludeBlocks(){
        List<User> people = userRepository.findAll();
        //List<Block> blocks = blockRepository.findAll();
        //List<String> blockNames = blocks.stream().map(Block::getName).collect(Collectors.toList());

        return people.stream().filter(user -> user.getBlock() == null).collect(Collectors.toList());
    }

    public UserResponseDto findById (Long id){
        User entity = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 아이디에 대한 내용이 없습니다. id =" + id));

        return new UserResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 아이디에 대한 내용이 없습니다. id= "+ id));

        if(requestDto.getRole() == Role.GUEST){
            user.update(Role.USER);
        }

        return id;
    }
}

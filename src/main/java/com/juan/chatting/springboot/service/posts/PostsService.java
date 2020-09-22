package com.juan.chatting.springboot.service.posts;

import com.juan.chatting.springboot.domain.posts.Posts;
import com.juan.chatting.springboot.domain.posts.PostsRepository;
import com.juan.chatting.springboot.web.dto.PostsListResponseDto;
import com.juan.chatting.springboot.web.dto.PostsResponseDto;
import com.juan.chatting.springboot.web.dto.PostsSaveRequestDto;
import com.juan.chatting.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id= "+ id));

        // 여기서 따로 update문이 필요 없는 이유는?
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}

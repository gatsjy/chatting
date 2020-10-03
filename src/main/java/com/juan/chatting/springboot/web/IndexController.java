package com.juan.chatting.springboot.web;

import com.juan.chatting.springboot.config.auth.LoginUser;
import com.juan.chatting.springboot.config.auth.dto.SessionUser;
import com.juan.chatting.springboot.service.person.UserService;
import com.juan.chatting.springboot.service.posts.PostsService;
import com.juan.chatting.springboot.web.dto.PostsResponseDto;
import com.juan.chatting.springboot.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final UserService userService;
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/user/update/{id}")
    public String userUpdate(@PathVariable Long id, Model model){
        UserResponseDto dto = userService.findById(id);
        model.addAttribute("user", dto);

        return "user-update";
    }

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("userRole", user.getRolekey());
            model.addAttribute("userId", user.getId());
        }
        return "index";
    }
}

package com.bst_aws.springboot.web.exercise;


import com.bst_aws.springboot.config.auth.LoginUser;
import com.bst_aws.springboot.config.auth.dto.SessionUser;
import com.bst_aws.springboot.service.posts.PostsService;
import com.bst_aws.springboot.service.vcount.VCountService;
import com.bst_aws.springboot.web.dto.exercise.PostsResponseDto;
import com.bst_aws.springboot.web.dto.request.VCountSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final VCountService vCountService;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        String userEmail = "visitor";
        if (user != null) {
            model.addAttribute("userName", user.getName());
            userEmail = user.getEmail();
        }
        /*// 방문자수 count
        VCountSaveRequestDto requestDto = new VCountSaveRequestDto();
        requestDto.builder()
                .userEmail(userEmail)
                .visitedDate(LocalDate.now().toString())
                .count(1)
                .build();
        vCountService.saveOrUpdate(requestDto);*/
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
            return "posts-save";
        }
        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
    @GetMapping("/test")
    public String test() {
        return "new_index";
    }


}
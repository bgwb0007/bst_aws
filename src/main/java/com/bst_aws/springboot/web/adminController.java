package com.bst_aws.springboot.web;


import com.bst_aws.springboot.config.auth.LoginUser;
import com.bst_aws.springboot.config.auth.dto.SessionUser;
import com.bst_aws.springboot.service.court.CourtService;
import com.bst_aws.springboot.service.lesson.LessonService;
import com.bst_aws.springboot.service.post.PostService;
import com.bst_aws.springboot.service.posts.PostsService;
import com.bst_aws.springboot.service.vcount.VCountService;
import com.bst_aws.springboot.web.dto.exercise.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class adminController {

    private final PostsService postsService;
    private final LessonService lessonService;
    private final PostService postService;
    private final CourtService courtService;
    private final VCountService vCountService;

    @GetMapping("/admin")
    public String admin(Model model, @LoginUser SessionUser user) {
        String userEmail = "visitor";
        if (user != null) {
            model.addAttribute("userName", user.getName());
            userEmail = user.getEmail();
        }
        return "admin/admin";
    }
    @GetMapping("/admin/court")
    public String court_save(Model model) {
        return "admin/court_save";
    }

    @GetMapping("/admin/lesson")
    public String lesson_save(Model model) {
        return "admin/lesson_save";
    }



}
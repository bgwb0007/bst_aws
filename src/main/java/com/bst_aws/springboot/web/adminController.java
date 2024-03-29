package com.bst_aws.springboot.web;


import com.bst_aws.springboot.config.auth.LoginUser;
import com.bst_aws.springboot.config.auth.dto.SessionUser;
import com.bst_aws.springboot.service.court.CourtService;
import com.bst_aws.springboot.service.lesson.LessonService;
import com.bst_aws.springboot.service.post.PostService;
import com.bst_aws.springboot.service.vcount.VCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class adminController {

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
    public String court_view(Model model) {
        model.addAttribute("courts", courtService.findAllAsc());
        return "admin/court_view";
    }
    @GetMapping("/admin/court/save")
    public String court_save(Model model) {
        return "admin/court_save";
    }

    @GetMapping("/admin/lesson")
    public String lesson_view(Model model) {
        model.addAttribute("lessons", lessonService.findAllAsc());
        return "admin/lesson_view";
    }

    @GetMapping("/admin/lesson/save")
    public String lesson_save(Model model) {
        return "admin/lesson_save";
    }



}
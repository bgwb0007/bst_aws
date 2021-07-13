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
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@Controller
public class IndexController {

    private final LessonService lessonService;
    private final PostService postService;
    private final CourtService courtService;
    private final VCountService vCountService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        String userEmail = "visitor";
        if (user != null) {
            model.addAttribute("userName", user.getName());
            userEmail = user.getEmail();
        }
        /*vCountService.saveOrUpdate(
                VCountSaveRequestDto.builder()
                .count(1)
                .visitedDate(LocalDate.now().toString())
                .userEmail(userEmail)
                .build());*/
        return "new_index";
    }

    @GetMapping("/test")
    public String test() {
        return "new_index";
    }

    @GetMapping("/court")
    public String court(Model model) {
        model.addAttribute("courts", courtService.findAllAsc());
        return "menu/court/court";
    }
    @GetMapping("/lesson")
    public String lesson(Model model) {
        model.addAttribute("lessons", lessonService.findAllAsc());
        return "menu/lesson/lesson";
    }

    @GetMapping("/post")
    public String post(Model model) {
        model.addAttribute("posts", postService.findAllDesc());
        return "menu/post/post";
    }

    @GetMapping("/post/save")
    public String postSave(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("user", user);
            return "menu/post/post-save";
        }
        return "menu/post/post";
    }

    @GetMapping("/post/{id}")
    public String postDetail(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("user",user);
        if (postService.isMyPost(id, user.getId())) {
            model.addAttribute("myPost", "myPost");
        }
        return "menu/post/post-detail";
    }

    @GetMapping("/post/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("user",user);
        return "menu/post/post-update";
    }



}
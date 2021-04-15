package com.bst_aws.springboot.web.controller;

import com.bst_aws.springboot.service.post.PostService;
import com.bst_aws.springboot.web.dto.request.PostSaveRequestDto;
import com.bst_aws.springboot.web.dto.request.PostUpdateRequestDto;
import com.bst_aws.springboot.web.dto.response.PostListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/v2/post")
    public Long save(@RequestBody PostSaveRequestDto requestDto){
        return postService.save(requestDto);
    }

    @PutMapping("/api/v2/post/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto){
        return postService.update(id,requestDto);
    }

    @DeleteMapping("/api/v2/post/{id}")
    public Long delete(@PathVariable Long id){
        postService.delete(id);
        return id;
    }

    @GetMapping("/api/v2/post/list")
    public List<PostListResponseDto> findAllDesc(){
        return postService.findAllDesc();
    }

}

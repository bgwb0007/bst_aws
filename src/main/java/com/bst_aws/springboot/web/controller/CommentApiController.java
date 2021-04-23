package com.bst_aws.springboot.web.controller;

import com.bst_aws.springboot.service.comment.CommentService;
import com.bst_aws.springboot.web.dto.request.CommentSaveRequestDto;
import com.bst_aws.springboot.web.dto.request.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

/*    @GetMapping("/api/v2/comment/{postId}")
    public List<CommentListResponseDto> findAllByPostId(@PathVariable Long postId){
        return commentService.findAllByPostId(postId);
    }*/
    @PostMapping("/api/v2/comment")
    public Long save(@RequestBody CommentSaveRequestDto requestDto){
        return commentService.save(requestDto);
    }

    @PutMapping("/api/v2/comment/{id}")
    public Long update(@PathVariable Long id, @RequestBody CommentUpdateRequestDto requestDto){
        return commentService.update(id,requestDto);
    }
    @DeleteMapping("/api/v2/comment/{id}")
    public Long delete(@PathVariable Long id){
        commentService.delete(id);
        return id;
    }

}

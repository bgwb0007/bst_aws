package com.bst_aws.springboot.web.dto.response;

import com.bst_aws.springboot.domain.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentListResponseDto {
    private Long id;
    private String content;
    private LocalDateTime createdDate;
    private Long postId;
    private String status;
    private String writer;

    public CommentListResponseDto(Comment entity){
        this.id = entity.getId();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedDate();
        this.postId = entity.getPost().getId();
        this.status = entity.getStatus();
        this.writer = entity.getUser().getName();
    }
}

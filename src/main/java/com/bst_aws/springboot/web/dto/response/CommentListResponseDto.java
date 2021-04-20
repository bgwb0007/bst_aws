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
    private Long userId;

    public CommentListResponseDto(Comment entity){
        this.id = entity.getId();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedDate();
        this.postId = entity.getPostId();
        this.status = entity.getStatus();
        this.userId = getUserId();
    }
}

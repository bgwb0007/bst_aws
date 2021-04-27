package com.bst_aws.springboot.web.dto.request;

import com.bst_aws.springboot.domain.comment.Comment;
import com.bst_aws.springboot.domain.post.Post;
import com.bst_aws.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDtoFront {

    private String content;
    private String status;
    private Long postId;
    private Long userId;

    @Builder
    public CommentSaveRequestDtoFront(String content, String status, Long postId, Long userId){
        this.content=content;
        this.status=status;
        this.postId=postId;
        this.userId=userId;

    }
}

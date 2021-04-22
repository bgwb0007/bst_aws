package com.bst_aws.springboot.web.dto.request;

import com.bst_aws.springboot.domain.comment.Comment;
import com.bst_aws.springboot.domain.post.Post;
import com.bst_aws.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDto {

    private String content;
    private String status;
    private Post post;
    private User user;

    @Builder
    public CommentSaveRequestDto(String content, String status, Post post, User user){
        this.content=content;
        this.status=status;
        this.post=post;
        this.user=user;
    }
    public Comment toEntity(){
        return Comment.builder()
                .content(content)
                .status(status)
                .post(post)
                .user(user)
                .build();
    }

}

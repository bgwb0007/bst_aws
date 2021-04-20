package com.bst_aws.springboot.web.dto.request;

import com.bst_aws.springboot.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDto {

    private String content;
    private String status;
    private Long postId;
    private Long userId;

    @Builder
    public CommentSaveRequestDto(String content, String status, Long postId, Long userId){
        this.content=content;
        this.status=status;
        this.postId=postId;
        this.userId=userId;
    }
    public Comment toEntity(){
        return Comment.builder()
                .content(content)
                .status(status)
                .postId(postId)
                .userId(userId)
                .build();
    }

}

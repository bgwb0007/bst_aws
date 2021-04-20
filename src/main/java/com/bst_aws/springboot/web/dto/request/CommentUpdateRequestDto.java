package com.bst_aws.springboot.web.dto.request;

import com.bst_aws.springboot.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentUpdateRequestDto {

    private String content;
    private String status;

    @Builder
    public CommentUpdateRequestDto(String content, String status){
        this.content =content;
        this.status=status;

    }
    public Comment toEntity(){
        return Comment.builder()
                .content(content)
                .status(status)
                .build();
    }

}

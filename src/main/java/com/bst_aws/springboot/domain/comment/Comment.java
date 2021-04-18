package com.bst_aws.springboot.domain.comment;

import com.bst_aws.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Text", nullable = false)
    private String content;

    private String status;

    private Long postId;

    private Long userId;

    @Builder
    public Comment(String content, String status, Long postId, Long userId){
        this.content=content;
        this.status=status;
        this.postId=postId;
        this.userId=userId;
    }


}

package com.bst_aws.springboot.domain.comment;

import com.bst_aws.springboot.domain.BaseTimeEntity;
import com.bst_aws.springboot.domain.post.Post;
import com.bst_aws.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

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

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;

    @Builder
    public Comment(String content, String status, Post post, User user){
        this.content=content;
        this.status=status;
        this.post = post;
        this.user = user;
    }

    public void update(Comment entity){
        this.content = entity.getContent();
        this.status = entity.getStatus();
    }


}

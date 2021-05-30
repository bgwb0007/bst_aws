package com.bst_aws.springboot.domain.comment;

import com.bst_aws.springboot.domain.BaseTimeEntity;
import com.bst_aws.springboot.domain.post.Post;
import com.bst_aws.springboot.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Data
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Text", nullable = false)
    private String content;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
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

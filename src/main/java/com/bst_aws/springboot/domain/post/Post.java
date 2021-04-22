package com.bst_aws.springboot.domain.post;

import com.bst_aws.springboot.domain.BaseTimeEntity;
import com.bst_aws.springboot.domain.comment.Comment;
import com.bst_aws.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String createdBy;

    private String dDay;

    private String district;

    private int hits;

    private String status;

    private String title;

    @ManyToOne
    @JoinColumn
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comment> commentList;

    @Builder
    public Post(String content, String createdBy, String dDay, String district, int hits,
                String status, String title, User user, List<Comment> commentList){
        this.content = content;
        this.createdBy = createdBy;
        this.dDay = dDay;
        this.district = district;
        this.hits = hits;
        this.status = status;
        this.title = title;
        this.user = user;
        this.commentList = commentList;
    }

    public void update(Post entity){
        this.content = entity.getContent();
        this.dDay = entity.getDDay();
        this.district = entity.getDistrict();
        this.status = entity.getStatus();
        this.title = entity.getTitle();
    }
    public void hits_update(){
        this.hits +=1;
    }

}

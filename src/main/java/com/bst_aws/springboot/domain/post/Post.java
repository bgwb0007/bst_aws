package com.bst_aws.springboot.domain.post;

import com.bst_aws.springboot.domain.BaseTimeEntity;
import com.bst_aws.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String createdBy;

    private LocalDateTime dDay;

    private String district;

    private int hits;

    private String status;

    private String title;

    private int userId;

    @Builder
    public Post(String content, String createdBy, LocalDateTime dDay, String district, int hits,
                String status, String title, int userId){
        this.content = content;
        this.createdBy = createdBy;
        this.dDay = dDay;
        this.district = district;
        this.hits = hits;
        this.status = status;
        this.title = title;
        this.userId = userId;
    }
}

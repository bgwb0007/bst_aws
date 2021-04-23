package com.bst_aws.springboot.web.dto.response;

import com.bst_aws.springboot.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class PostListResponseDto {
    private Long id;
    private String createdBy;
    private String dDay;
    private String district;
    private int hits;
    private String status;
    private String title;
    private Long userId;
    private String createdDate;

    public PostListResponseDto(Post entity){
        this.id = entity.getId();
        this.createdBy = entity.getCreatedBy();
        this.dDay = entity.getDDay();
        this.district=entity.getDistrict();
        this.hits=entity.getHits();
        this.status=entity.getStatus();
        this.title=entity.getTitle();
        this.userId=entity.getUser().getId();
        String temp =entity.getCreatedDate().toString();
        this.createdDate= temp.substring(2,10) + "   " + temp.substring(11,13) + "시 " + temp.substring(14,16) + "분";
    }

}

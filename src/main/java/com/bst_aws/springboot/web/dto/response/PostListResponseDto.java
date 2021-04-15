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
    private int userId;
    private LocalDateTime createdDate;

    public PostListResponseDto(Post entity){
        this.id = entity.getId();
        this.createdBy = entity.getCreatedBy();
        this.dDay = entity.getDDay();
        this.district=entity.getDistrict();
        this.hits=entity.getHits();
        this.status=entity.getStatus();
        this.title=entity.getTitle();
        this.userId=entity.getUserId();
        this.createdDate=entity.getCreatedDate();
    }

}

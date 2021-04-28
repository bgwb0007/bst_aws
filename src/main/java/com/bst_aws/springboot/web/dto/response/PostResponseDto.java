package com.bst_aws.springboot.web.dto.response;

import com.bst_aws.springboot.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;
    private String content;
    private String dDay;
    private String district;
    private int hits;
    private String status;
    private String title;
    private Long userId;
    private String userName;

    @Builder
    public PostResponseDto(Post entity){
        this.id = entity.getId();
        this.content=entity.getContent();
        this.dDay=entity.getDDay();
        this.district=entity.getDistrict();
        this.hits=entity.getHits();
        this.status=entity.getStatus();
        this.title=entity.getTitle();
        this.userId= entity.getUser().getId();
        this.userName= entity.getUser().getName();
    }


}

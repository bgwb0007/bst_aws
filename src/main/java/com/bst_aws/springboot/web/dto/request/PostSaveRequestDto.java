package com.bst_aws.springboot.web.dto.request;

import com.bst_aws.springboot.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {

    private String content;
    private String createdBy;
    private String dDay;
    private String district;
    private String title;
    private int userId;

    @Builder
    public PostSaveRequestDto(String content, String createdBy, String dDay,
                              String district, String title, int userId){
        this.content = content;
        this.createdBy = createdBy;
        this.dDay = dDay;
        this.district = district;
        this.title = title;
        this.userId = userId;
    }
    public Post toEntity(){
        return Post.builder()
                .content(content)
                .createdBy(createdBy)
                .dDay(dDay)
                .district(district)
                .title(title)
                .userId(userId)
                .build();
    }

}

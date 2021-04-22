package com.bst_aws.springboot.web.dto.request;

import com.bst_aws.springboot.domain.post.Post;
import com.bst_aws.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {

    private String content;
    private String createdBy;
    private String dDay;
    private String district;
    private String title;
    private User user;

    @Builder
    public PostSaveRequestDto(String content, String createdBy, String dDay,
                              String district, String title, User user){
        this.content = content;
        this.createdBy = createdBy;
        this.dDay = dDay;
        this.district = district;
        this.title = title;
        this.user = user;
    }
    public Post toEntity(){
        return Post.builder()
                .content(content)
                .createdBy(createdBy)
                .dDay(dDay)
                .district(district)
                .title(title)
                .user(user)
                .build();
    }

}

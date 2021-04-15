package com.bst_aws.springboot.web.dto.request;

import com.bst_aws.springboot.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostUpdateRequestDto {
    private String content;
    private String dDay;
    private String district;
    private String status;
    private String title;

    @Builder
    public PostUpdateRequestDto(String content, String dDay, String district,
                                String status, String title){
        this.content = content;
        this.dDay=dDay;
        this.district=district;
        this.status=status;
        this.title=title;
    }
    public Post toEntity(){
        return Post.builder()
                .content(content)
                .dDay(dDay)
                .district(district)
                .status(status)
                .title(title)
                .build();
    }
}

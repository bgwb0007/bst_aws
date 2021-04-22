package com.bst_aws.springboot.web.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDtoFront {

    private String content;
    private String createdBy;
    private String dDay;
    private String district;
    private String title;
    private Long userId;

    @Builder
    public PostSaveRequestDtoFront(String content, String createdBy, String dDay,
                                   String district, String title, Long userId){
        this.content=content;
        this.createdBy= createdBy;
        this.dDay=dDay;
        this.district=district;
        this.title=title;
        this.userId=userId;
    }

}

package com.bst_aws.springboot.web.dto.request;

import com.bst_aws.springboot.domain.vcount.VCount;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VCountSaveRequestDto {

    private Integer count;
    private String visitedDate;
    private String userEmail;

    @Builder
    public VCountSaveRequestDto(String visitedDate, String userEmail, int count){
        this.count=count;
        this.visitedDate=visitedDate;
        this.userEmail=userEmail;
    }
    public VCount toEntity(){
        return VCount.builder()
                .count(count)
                .visitedDate(visitedDate)
                .userEmail(userEmail)
                .build();
    }

}

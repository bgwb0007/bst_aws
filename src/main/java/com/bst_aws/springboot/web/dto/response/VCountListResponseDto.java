package com.bst_aws.springboot.web.dto.response;

import com.bst_aws.springboot.domain.vcount.VCount;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class VCountListResponseDto {
    private Long id;
    private Integer count;
    private String visitedDate;
    private String userEmail;

    public VCountListResponseDto(VCount entity){
        this.id=entity.getId();
        this.count=entity.getCount();
        this.visitedDate=entity.getVisitedDate();
        this.userEmail=entity.getUserEmail();
    }
}

package com.bst_aws.springboot.web.dto.request;

import com.bst_aws.springboot.domain.vcount.VCount;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class VCountUpdateRequestDto {
    private Integer count;

    @Builder
    public VCountUpdateRequestDto(Integer count){
        this.count=count;
    }

}

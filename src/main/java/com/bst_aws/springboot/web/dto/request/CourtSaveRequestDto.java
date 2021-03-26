package com.bst_aws.springboot.web.dto.request;

import com.bst_aws.springboot.domain.court.Court;
import com.bst_aws.springboot.domain.lesson.Lesson;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CourtSaveRequestDto {

    private String name;
    private String address;
    private String web;
    private String info;
    private String district;
    private Double e;
    private Double n;

    @Builder
    public CourtSaveRequestDto(String name, String address, String web,
                               String info, String district, Double e, Double n){
        this.name = name;
        this.address = address;
        this.web = web;
        this.info = info;
        this.district = district;
        this.e = e;
        this.n = n;
    }
    public Court toEntity(){
        return Court.builder()
                .name(name)
                .address(address)
                .web(web)
                .info(info)
                .district(district)
                .e(e)
                .n(n)
                .build();
    }

}

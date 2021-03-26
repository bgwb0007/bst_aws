package com.bst_aws.springboot.web.dto.response;

import com.bst_aws.springboot.domain.lesson.Lesson;
import lombok.Getter;

@Getter
public class LessonListResponseDto {
    private Long id;
    private String name;
    private String address;
    private String web;
    private String info;
    private String district;

    public LessonListResponseDto(Lesson entity){
        this.id = entity.getId();
        this.name=entity.getName();
        this.address = entity.getAddress();
        this.web = entity.getWeb();
        this.info = entity.getInfo();
        this.district = entity.getDistrict();
    }
}

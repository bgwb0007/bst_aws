package com.bst_aws.springboot.domain.lesson;

import com.bst_aws.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Lesson extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String web;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String info;

    private String district;

    private Double e;

    private Double n;

    @Builder
    public Lesson(String name, String address, String web, String info, String district, Double n, Double e){
        this.name = name;
        this.address = address;
        this.web = web;
        this.info = info;
        this.district =district;
        this.e = e;
        this.n = n;
    }

}

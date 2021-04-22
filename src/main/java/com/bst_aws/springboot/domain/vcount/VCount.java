package com.bst_aws.springboot.domain.vcount;

import com.bst_aws.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class VCount extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer count;

    private String visitedDate;

    private String userEmail;

    @Builder
    public VCount(Integer count, String visitedDate, String userEmail){
        this.count = count;
        this.visitedDate = visitedDate;
        this.userEmail = userEmail;
    }
    public VCount update(Integer count){
        this.count = count;
        return this;
    }

    public void visited_check(){
        this.count +=1;
    }
}

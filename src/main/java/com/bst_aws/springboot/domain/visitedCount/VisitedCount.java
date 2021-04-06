package com.bst_aws.springboot.domain.visitedCount;

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
public class VisitedCount extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer count;

    private String createdBy;

    private Long userId;

    @Builder
    public VisitedCount(Integer count, String createdBy, Long userId){
        this.count = count;
        this.createdBy = createdBy;
        this.userId = userId;
    }
    public VisitedCount update(Integer count){
        this.count = count;
        return this;
    }
}

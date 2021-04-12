package com.bst_aws.springboot.domain.posts;


import com.bst_aws.springboot.domain.vcount.VCount;
import com.bst_aws.springboot.domain.vcount.VCountRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VCountRepositoryTest {

    @Autowired
    VCountRepository vCountRepository;

    @After
    public void cleanup(){
        vCountRepository.deleteAll();}

    @Test
    public void 방문자수저장_불러오기(){
        //given
        int count = 77;
        String createdBy = "user1";
        long userId = 33;

        vCountRepository.save(VCount.builder()
                .count(count)
                .createdBy(createdBy)
                .userId(userId)
                .build());
        //when
        List<VCount> VCountList = vCountRepository.findAllDesc();

        //then
        VCount vcount = VCountList.get(0);
        assertThat(vcount.getCount()).isEqualTo(count);
        assertThat(vcount.getCreatedBy()).isEqualTo(createdBy);

    }



}

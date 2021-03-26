package com.bst_aws.springboot.domain.posts;

import com.bst_aws.springboot.domain.court.Court;
import com.bst_aws.springboot.domain.court.CourtRepository;
import com.bst_aws.springboot.domain.lesson.Lesson;
import com.bst_aws.springboot.domain.lesson.LessonRepository;
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
public class CourtRepositoryTest {

    @Autowired
    CourtRepository courtRepository;
    @After
    public void cleanup(){
        courtRepository.deleteAll();
    }

    @Test
    public void 코트저장_불러오기(){
        //givne
        String name = "구덕운동장";
        String address = "부산시 서구 구덕운동장";
        String web = "http://www.rnejrejrje.com";
        String info = "레슨받기 좋아요";
        String district = "서구";
        Double e = 3.2222;
        Double n = 32.22201;

        courtRepository.save(Court.builder()
                .name(name)
                .address(address)
                .web(web)
                .info(info)
                .district(district)
                .e(e)
                .n(n)
                .build());
        //when
        List<Court> courtList = courtRepository.findAll();

        //then
        Court court = courtList.get(0);
        assertThat(court.getName()).isEqualTo(name);
        assertThat(court.getDistrict()).isEqualTo(district);

    }

}

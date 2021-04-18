package com.bst_aws.springboot.domain.posts;

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
public class LessonRepositoryTest {

    @Autowired
    LessonRepository lessonRepository;
    @After
    public void cleanup(){
        lessonRepository.deleteAll();
    }

    @Test
    public void 레슨저장_불러오기(){
        //givne
        String name = "구덕운동장";
        String address = "부산시 서구 구덕운동장";
        String web = "http://www.rnejrejrje.com";
        String info = "레슨받기 좋아요";
        String district = "서구";
        Double e = 3.2222;
        Double n = 32.22201;

        lessonRepository.save(Lesson.builder()
                .name(name)
                .address(address)
                .web(web)
                .info(info)
                .district(district)
                .e(e)
                .n(n)
                .build());
        //when
        List<Lesson> lessonList = lessonRepository.findAll();

        //then
        Lesson lesson = lessonList.get(0);
        assertThat(lesson.getName()).isEqualTo(name);
        assertThat(lesson.getDistrict()).isEqualTo(district);

    }

    @Test
    public void 지역목록_불러오기(){
        //givne
        lessonRepository.save(Lesson.builder()
                .name("구덕운동장")
                .address("부산시 서구 구덕운동장")
                .web("http://www.rnejrejrje.com")
                .info("레슨받기 좋아요")
                .district("서구")
                .e(3.2222)
                .n(32.22201)
                .build());
        lessonRepository.save(Lesson.builder()
                .name("사직실내")
                .address("사직동")
                .web("web-wwww.w.w.w..w.w")
                .info("실내체육관")
                .district("동래구")
                .e(2.3333)
                .n(10.22)
                .build());
        //when
        List<String> districtList = lessonRepository.findAllDistrictList();
        //then
        String d1 = districtList.get(0);
        assertThat(d1).isEqualTo("동래구");

        String d2 = districtList.get(1);
        assertThat(d2).isEqualTo("서구");

    }

}

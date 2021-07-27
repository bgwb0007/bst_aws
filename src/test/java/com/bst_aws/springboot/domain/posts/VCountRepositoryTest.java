package com.bst_aws.springboot.domain.posts;


import com.bst_aws.springboot.domain.vcount.VCount;
import com.bst_aws.springboot.domain.vcount.VCountRepository;
import com.bst_aws.springboot.web.dto.request.VCountSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        int count = 1;
        String visitedDate = LocalDate.now().toString();
        String userEmail = "bgwb0007@naver.com";


        vCountRepository.save(VCount.builder()
                .count(count)
                .visitedDate(visitedDate)
                .userEmail(userEmail)
                .build());
        //when
        List<VCount> VCountList = vCountRepository.findAllDesc();

        //then
        VCount vcount = VCountList.get(0);
        assertThat(vcount.getCount()).isEqualTo(count);
        assertThat(vcount.getVisitedDate()).isEqualTo(visitedDate);

    }

    @Test
    public void 이미생성됐는지_확인_date_email(){
        //given
        int count = 22;
        String visitedDate = LocalDate.now().toString();
        String userEmail = "tesssst@naver.com";
        String now_s = LocalDate.now().toString();
        String dDay = LocalDate.of(2021, 07, 24).toString();

        vCountRepository.save(VCount.builder()
                .count(count)
                .visitedDate(visitedDate)
                .userEmail(userEmail)
                .build());

        // dDay 날짜에 userEmail 비어있는 데이터 생성
        vCountRepository.save(VCount.builder()
                .count(count)
                .visitedDate(dDay)
                .build());


        //when
        Boolean isVisited_t = vCountRepository.existsByVisitedDateContainingAndUserEmail(now_s, userEmail);
        Boolean isVisited_f = vCountRepository.existsByVisitedDateContainingAndUserEmail(now_s, null);
        Boolean dDay_f = vCountRepository.existsByVisitedDateContainingAndUserEmail(dDay, userEmail);
        Boolean dDay_t = vCountRepository.existsByVisitedDateContainingAndUserEmail(dDay, null);

        //then
        assertThat(isVisited_t).isTrue();
        assertThat(isVisited_f).isFalse();
        assertThat(dDay_t).isTrue();
        assertThat(dDay_f).isFalse();
    }

    // 방문기록_ 없으면 생성, 있으면 count +1
    @Test
    public void saveOrUpdate_테스트() {
        //given
        int count = 22;
        String visitedDate = LocalDate.now().toString();
        String userEmail = "bgwb0007@naver.com";
        String now_s = LocalDate.now().toString();
        String dDay = LocalDate.of(2021, 07, 24).toString();

        vCountRepository.save(VCount.builder()
                .count(count)
                .visitedDate(visitedDate)
                .userEmail(userEmail)
                .build());

        VCountSaveRequestDto requestDto = new VCountSaveRequestDto(dDay,null,1);

        //saveOrUpdate
        VCount vCount = vCountRepository.findByVisitedDateContainingAndUserEmail(visitedDate, null)
                .map(entity -> entity.update(entity.getCount()+1))
                .orElse(requestDto.toEntity());

        vCountRepository.save(vCount);

        VCount vCount_test = vCountRepository.findAllDesc().get(0);
        //then
        assertThat(vCount_test.getCount()).isEqualTo(1);

    }
}

package com.bst_aws.springboot.web;

import com.bst_aws.springboot.domain.court.Court;
import com.bst_aws.springboot.domain.court.CourtRepository;
import com.bst_aws.springboot.domain.lesson.Lesson;
import com.bst_aws.springboot.domain.lesson.LessonRepository;
import com.bst_aws.springboot.web.dto.request.CourtSaveRequestDto;
import com.bst_aws.springboot.web.dto.request.LessonSaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourtApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private CourtRepository courtRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    @After
    public void tearDown() throws Exception{
        courtRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void Court_등록된다() throws Exception{
        //given
        String name = "구덕운동장";
        String address = "부산시 서구 구덕운덩장";
        String web = "http://localhost:2222";
        String info = "인조잔디 구장";
        Double e = 2.33232;
        Double n = 3.4422;
        String district = "서구";
        CourtSaveRequestDto requestDto = CourtSaveRequestDto.builder()
                .name(name)
                .address(address)
                .web(web)
                .info(info)
                .district(district)
                .e(e)
                .n(n)
                .build();
        String url = "http://localhost:"+port+"/api/v2/court";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Court> all = courtRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getDistrict()).isEqualTo(district);

    }
}

package com.bst_aws.springboot.web;

import com.bst_aws.springboot.domain.post.Post;
import com.bst_aws.springboot.domain.post.PostRepository;
import com.bst_aws.springboot.domain.user.Role;
import com.bst_aws.springboot.domain.user.User;
import com.bst_aws.springboot.domain.user.UserRepository;
import com.bst_aws.springboot.web.dto.request.PostSaveRequestDtoFront;
import com.bst_aws.springboot.web.dto.request.PostUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        User user = User.builder()
                .email("kkk@naver.com")
                .name("서형이")
                .picture("ass")
                .role(Role.USER)
                .build();
        userRepository.save(user);

        Post post = Post.builder()
                .user(userRepository.getOne(1L))
                .hits(1)
                .status("on")
                .dDay(LocalDate.now().toString())
                .district("사상구")
                .content("상대구함")
                .title("오늘칠사람")
                .createdBy("서형이_createdBy")
                .build();
        postRepository.save(post);
    }

    @After
    public void tearDown() throws Exception {
        postRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void Post_등록() throws Exception {
        //given
        String title = "title";
        String content = "content";
        String district = "해운대구";
        String createdBy = "user1";
        String dDay = LocalDate.of(2021, 04, 12).toString();
        Long userId = 1L;


        PostSaveRequestDtoFront requestDtoFront = PostSaveRequestDtoFront.builder()
                .title(title)
                .content(content)
                .district(district)
                .userId(userId)
                .createdBy(createdBy)
                .dDay(dDay)
                .build();

        String url = "http://localhost:" + port + "/api/v2/post";

        //when

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDtoFront)))
                .andExpect(status().isOk());

        //then
        List<Post> all = postRepository.findAllOrderByCreatedDateDesc();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(LocalDate.from(all.get(0).getCreatedDate())).isAfter(LocalDate.parse(dDay));
    }


    @Test
    @WithMockUser(roles="USER")
    public void Post_수정() throws Exception {
        //given

        Long postId = 1L;
        String content = "수정된 글";
        String dDay = LocalDate.of(2021,04,22).toString();
        String district = "수영구";
        String status = "on";
        String title = "수정된 제목";

        PostUpdateRequestDto requestDto = PostUpdateRequestDto.builder()
                .content(content)
                .dDay(dDay)
                .district(district)
                .status(status)
                .title(title)
                .build();

        String url = "http://localhost:" + port + "/api/v2/post/" + postId;

        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getUser().getName()).isEqualTo("서형이");
        assertThat(all.get(0).getId()).isEqualTo(postId);
    }

}

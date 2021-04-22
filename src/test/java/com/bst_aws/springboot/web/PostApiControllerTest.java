package com.bst_aws.springboot.web;

import com.bst_aws.springboot.domain.post.PostRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

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
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        postRepository.deleteAll();
    }

    /*@Test
    @WithMockUser(roles="USER")
    public void Post_등록() throws Exception {
        //given
        String title = "title";
        String content = "content";
        String district = "해운대구";
        Long userId = 3L;
        String createdBy = "user1";
        String dDay = LocalDate.of(2021, 04, 12).toString();

        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
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
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(LocalDate.from(all.get(0).getCreatedDate())).isAfter(LocalDate.parse(dDay));
    }


    @Test
    @WithMockUser(roles="USER")
    public void Post_수정() throws Exception {
        //given
        String title = "title";
        String content = "content";
        String district = "해운대구";
        Long userId = 3L;
        String createdBy = "user1";
        String dDay = LocalDate.of(2021,04,13).toString();
        Post savedPost = postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .district(district)
                .user(userId)
                .createdBy(createdBy)
                .dDay(dDay)
                .build());

        Long updateId = savedPost.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostUpdateRequestDto requestDto = PostUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v2/post/" + updateId;

        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }*/


}

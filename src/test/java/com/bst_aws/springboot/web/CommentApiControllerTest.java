package com.bst_aws.springboot.web;

import com.bst_aws.springboot.domain.comment.Comment;
import com.bst_aws.springboot.domain.comment.CommentRepository;
import com.bst_aws.springboot.domain.post.Post;
import com.bst_aws.springboot.domain.post.PostRepository;
import com.bst_aws.springboot.domain.user.Role;
import com.bst_aws.springboot.domain.user.User;
import com.bst_aws.springboot.domain.user.UserRepository;
import com.bst_aws.springboot.web.dto.request.CommentSaveRequestDto;
import com.bst_aws.springboot.web.dto.request.CommentUpdateRequestDto;
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
public class CommentApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
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

        User user1 = User.builder()
                .name("서형")
                .email("bgwb00@naver.com")
                .picture("kk")
                .role(Role.USER)
                .build();
        userRepository.save(user1);

        Post post1 = Post.builder()
                .content("내용")
                .user(userRepository.findAll().get(0))
                .dDay(LocalDate.now().toString())
                .district("사상구")
                .hits(1)
                .status("on")
                .createdBy("서형이")
                .title("제목")
                .build();
        postRepository.save(post1);
    }
    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
        postRepository.deleteAll();
        commentRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void Comment_등록() throws Exception{

        Post post1 = postRepository.getOne(1L);
        User user1 = userRepository.getOne(1L);

        String content = "참가합니다";
        String status = "on";

        CommentSaveRequestDto requestDto = CommentSaveRequestDto.builder()
                .content(content)
                .status(status)
                .post(post1)
                .user(user1)
                .build();


        String url = "http://localhost:" + port + "/api/v2/comment";

        
        //param > 객체 테스트방법
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto.toEntity())))
                .andExpect(status().isOk());

        List<Comment> all = commentRepository.findAll();
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getPost().getId()).isEqualTo(post1.getId());

    }
    @Test
    @WithMockUser(roles="USER")
    public void Comment_수정() throws Exception {
        //given
        String content = "참가합니다";
        String status = "on";
        Long postId = 1L;
        Long userId = 33L;
        User user = userRepository.findAll().get(0);
        Post post = postRepository.findAll().get(0);

        Comment saveComment = commentRepository.save(Comment.builder()
                .content(content)
                .status(status)
                .post(post)
                .user(user)
                .build());

        Long commentId = saveComment.getId();
        String new_status = "off";

        CommentUpdateRequestDto requestDto = CommentUpdateRequestDto.builder()
                .status(new_status)
                .content(content)
                .build();
        String url = "http://localhost:" + port + "/api/v2/comment/" + commentId;
        
        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Comment> all = commentRepository.findAll();
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getStatus()).isEqualTo(new_status);
    }
}

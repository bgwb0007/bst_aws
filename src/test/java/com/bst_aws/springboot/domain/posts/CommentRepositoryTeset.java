package com.bst_aws.springboot.domain.posts;

import com.bst_aws.springboot.domain.comment.Comment;
import com.bst_aws.springboot.domain.comment.CommentRepository;
import com.bst_aws.springboot.domain.post.Post;
import com.bst_aws.springboot.domain.post.PostRepository;
import com.bst_aws.springboot.domain.user.Role;
import com.bst_aws.springboot.domain.user.User;
import com.bst_aws.springboot.domain.user.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTeset {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;


    @Before
    public void setup() {

        User user1 = User.builder()
                .name("서형")
                .email("bgwb00@naver.com")
                .picture("kk")
                .role(Role.USER)
                .build();
        userRepository.save(user1);

        Post post1 = Post.builder()
                .content("내용")
                .user(user1)
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
    public void cleanup(){
        commentRepository.deleteAll();
    }

    @Test
    public void 댓글저장_불러오기(){
        //given
        String content = "테스트 게시글";
        String status = "on";

        Post post = postRepository.findAll().get(0);
        User user = userRepository.findAll().get(0);

        commentRepository.save(Comment.builder()
                .content(content)
                .status(status)
                .post(post)
                .user(user)
                .build());

        //when
        List<Comment> commentList = commentRepository.findAll();

        //then
        Comment comment = commentList.get(0);
        assertThat(comment.getContent()).isEqualTo(content);
        assertThat(comment.getUser().getId()).isEqualTo(user.getId());
        assertThat(comment.getPost().getId()).isEqualTo(post.getId());
    }
}

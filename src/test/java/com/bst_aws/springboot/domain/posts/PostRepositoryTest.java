package com.bst_aws.springboot.domain.posts;

import com.bst_aws.springboot.domain.post.Post;
import com.bst_aws.springboot.domain.post.PostRepository;
import com.bst_aws.springboot.domain.user.Role;
import com.bst_aws.springboot.domain.user.User;
import com.bst_aws.springboot.domain.user.UserRepository;
import javafx.geometry.Pos;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception{
        User user = User.builder()
                .email("test1@naver.com")
                .name("유저1번")
                .picture("kkkksadf")
                .role(Role.USER)
                .build();
        userRepository.save(user);

        Post post = Post.builder()
                .title("테스트게시글")
                .content("테스트 본문")
                .createdBy("서형")
                .dDay(LocalDate.of(2021, 04, 4).toString())
                .district("수영구")
                .hits(1)
                .status("on")
                .user(userRepository.getOne(1L))
                .build();
        postRepository.save(post);
    }

    @After
    public void cleanup(){
        postRepository.deleteAll();
    }

    @Test
    public void post_저장되었는지_불러오기() {
        Post post = postRepository.findAll().get(0);
        String title = post.getTitle();
        assertThat(title).isEqualTo("테스트게시글");

        String name = post.getUser().getName();
        assertThat(name).isEqualTo("유저1번");
        System.out.println("유저 id= "+post.getUser().getId());

        /* 에러 뜨는거 해결못함.
        List<Post> postList = post.getUser().getPostList();
        for(Post p : postList){
            assertThat(p.getCreatedBy()).startsWith("서형");
        }*/

    }

    @Test
    public void BaseTimeEntity_등록_불러오기() {

        Post post = postRepository.findAll().get(0);

        System.out.println(">>>>>>>>> createDate=" + post.getCreatedDate() + ", modifiedDate=" + post.getModifiedDate()+" D-Day="+post.getDDay());
        String dDay = post.getDDay();
        assertThat(LocalDate.from(post.getCreatedDate())).isAfter(dDay);
        assertThat(LocalDate.from(post.getModifiedDate())).isAfter(dDay);
    }
}

package com.bst_aws.springboot.domain.posts;

import com.bst_aws.springboot.domain.post.Post;
import com.bst_aws.springboot.domain.post.PostRepository;
import org.junit.After;
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

    @After
    public void cleanup(){
        postRepository.deleteAll();
    }
    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String createdBy="서형";
        String dDay = LocalDate.of(2021, 04, 4).toString();
        String district = "수영구";
        int hits = 0;
        String status = "on";
        int userId = 1;


        postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .createdBy(createdBy)
                .dDay(dDay)
                .district(district)
                .hits(hits)
                .status(status)
                .userId(userId)
                .build());

        //when
        List<Post> postList = postRepository.findAll();

        //then
        Post post = postList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        String dDay = LocalDate.of(2021, 04, 4).toString();
        postRepository.save(Post.builder()
                .title("title")
                .content("content")
                .createdBy("createdBy")
                .dDay(dDay)
                .district("district")
                .hits(1)
                .status("status")
                .userId(1)
                .build());
        //when
        List<Post> postList = postRepository.findAll();

        //then
        Post post = postList.get(0);

        System.out.println(">>>>>>>>> createDate=" + post.getCreatedDate() + ", modifiedDate=" + post.getModifiedDate()+" D-Day="+post.getDDay());

        assertThat(LocalDate.from(post.getCreatedDate())).isAfter(dDay);
        assertThat(LocalDate.from(post.getModifiedDate())).isAfter(dDay);
    }
}

package com.bst_aws.springboot.domain.posts;

import com.bst_aws.springboot.domain.comment.Comment;
import com.bst_aws.springboot.domain.comment.CommentRepository;
import com.bst_aws.springboot.domain.post.Post;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTeset {
    @Autowired
    CommentRepository commentRepository;

    @After
    public void cleanup(){
        commentRepository.deleteAll();
    }
    @Test
    public void 댓글저장_불러오기(){
        //given
        String content = "테스트 게시글";
        String status = "on";
        Long postId = 77L;
        Long userId = 1L;


        commentRepository.save(Comment.builder()
                .content(content)
                .status(status)
                .postId(postId)
                .userId(userId)
                .build());

        commentRepository.save(Comment.builder()
                .content(content+ "22")
                .status(status)
                .postId(postId)
                .userId(userId+1L)
                .build());

        //when
        List<Comment> commentList = commentRepository
                .findAllByPostIdOrderByCreatedDateAsc(postId);

        //then
        Comment comment = commentList.get(0);
        assertThat(comment.getContent()).isEqualTo(content);
        assertThat(comment.getUserId()).isEqualTo(userId);
        assertThat(comment.getPostId()).isEqualTo(postId);
    }

}

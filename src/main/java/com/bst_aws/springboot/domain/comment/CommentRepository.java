package com.bst_aws.springboot.domain.comment;

import com.bst_aws.springboot.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.post.id = :postId order by c.createdDate asc ")
    List<Comment> findAllByPostIdOrderByCreatedDateAsc(@Param("postId") Long postId);
}

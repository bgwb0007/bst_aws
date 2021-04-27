package com.bst_aws.springboot.service.comment;

import com.bst_aws.springboot.domain.comment.Comment;
import com.bst_aws.springboot.domain.comment.CommentRepository;
import com.bst_aws.springboot.domain.post.PostRepository;
import com.bst_aws.springboot.domain.user.UserRepository;
import com.bst_aws.springboot.web.dto.request.CommentSaveRequestDto;
import com.bst_aws.springboot.web.dto.request.CommentSaveRequestDtoFront;
import com.bst_aws.springboot.web.dto.request.CommentUpdateRequestDto;
import com.bst_aws.springboot.web.dto.response.CommentListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<CommentListResponseDto> findAllByPostId(Long postId){
        return commentRepository.findAllByPostIdOrderByCreatedDateAsc(postId).stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public Long save(CommentSaveRequestDtoFront requestDtoFront){
        CommentSaveRequestDto requestDto = CommentSaveRequestDto.builder()
                .content(requestDtoFront.getContent())
                .status(requestDtoFront.getStatus())
                .post(postRepository.getOne(requestDtoFront.getPostId()))
                .user(userRepository.getOne(requestDtoFront.getUserId()))
                .build();

        return commentRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, CommentUpdateRequestDto requestDto){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 댓글이 없습니다. id="+id));
        comment.update(requestDto.toEntity());
        return id;
    }
    @Transactional
    public void delete(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다. id="+id));
        commentRepository.delete(comment);
    }
}

package com.bst_aws.springboot.service.comment;

import com.bst_aws.springboot.domain.comment.Comment;
import com.bst_aws.springboot.domain.comment.CommentRepository;
import com.bst_aws.springboot.web.dto.request.CommentSaveRequestDto;
import com.bst_aws.springboot.web.dto.request.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

/*    @Transactional(readOnly = true)
    public List<CommentListResponseDto> findAllByPostId(Long postId){
        return commentRepository.findAll().stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
    }*/
    @Transactional
    public Long save(CommentSaveRequestDto requestDto){
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

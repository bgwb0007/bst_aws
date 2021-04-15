package com.bst_aws.springboot.service.post;

import com.bst_aws.springboot.domain.post.Post;
import com.bst_aws.springboot.domain.post.PostRepository;
import com.bst_aws.springboot.web.dto.request.PostSaveRequestDto;
import com.bst_aws.springboot.web.dto.request.PostUpdateRequestDto;
import com.bst_aws.springboot.web.dto.response.PostListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto){
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto){
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 글이 없습니다. id="+id));
        post.update(requestDto.toEntity());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 글이 없습니다. id="+id));
        postRepository.delete(post);
    }

    @Transactional
    public List<PostListResponseDto> findAllDesc(){
        return postRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }



}

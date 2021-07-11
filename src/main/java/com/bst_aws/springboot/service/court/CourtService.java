package com.bst_aws.springboot.service.court;

import com.bst_aws.springboot.domain.court.Court;
import com.bst_aws.springboot.domain.court.CourtRepository;
import com.bst_aws.springboot.domain.lesson.LessonRepository;
import com.bst_aws.springboot.domain.post.Post;
import com.bst_aws.springboot.web.dto.request.CourtSaveRequestDto;
import com.bst_aws.springboot.web.dto.request.LessonSaveRequestDto;
import com.bst_aws.springboot.web.dto.response.CourtListResponseDto;
import com.bst_aws.springboot.web.dto.response.LessonListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CourtService {
    private final CourtRepository courtRepository;

    @Transactional
    public Long save(CourtSaveRequestDto requestDto){
        return courtRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public void delete(Long id){
        Court court = courtRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 코트가 없습니다. id="+id));
        courtRepository.delete(court);
    }

    @Transactional(readOnly = true)
    public List<CourtListResponseDto> findAllAsc(){
        return courtRepository.findAllAsc().stream()
                .map(CourtListResponseDto::new)
                .collect(Collectors.toList());
    }

}

package com.bst_aws.springboot.service.lesson;

import com.bst_aws.springboot.domain.lesson.LessonRepository;
import com.bst_aws.springboot.web.dto.request.LessonSaveRequestDto;
import com.bst_aws.springboot.web.dto.response.LessonListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    @Transactional
    public Long save(LessonSaveRequestDto requestDto){
        return lessonRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<LessonListResponseDto> findAllAsc(){
        return lessonRepository.findAllAsc().stream()
                .map(LessonListResponseDto::new)
                .collect(Collectors.toList());
    }

}

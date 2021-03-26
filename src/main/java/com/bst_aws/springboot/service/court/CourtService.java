package com.bst_aws.springboot.service.court;

import com.bst_aws.springboot.domain.court.CourtRepository;
import com.bst_aws.springboot.domain.lesson.LessonRepository;
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

    @Transactional(readOnly = true)
    public List<CourtListResponseDto> findAllAsc(){
        return courtRepository.findAllAsc().stream()
                .map(CourtListResponseDto::new)
                .collect(Collectors.toList());
    }

}

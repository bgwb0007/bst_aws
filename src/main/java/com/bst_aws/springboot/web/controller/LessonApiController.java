package com.bst_aws.springboot.web.controller;

import com.bst_aws.springboot.service.lesson.LessonService;
import com.bst_aws.springboot.web.dto.request.LessonSaveRequestDto;
import com.bst_aws.springboot.web.dto.response.LessonListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LessonApiController {

    private final LessonService lessonService;

    @PostMapping("/api/v2/lesson")
    public Long save(@RequestBody LessonSaveRequestDto requestDto){
        return lessonService.save(requestDto);
    }

    @GetMapping("/api/v2/lesson/list")
    public List<LessonListResponseDto> findAllAsc(){
        return lessonService.findAllAsc();
    }
}

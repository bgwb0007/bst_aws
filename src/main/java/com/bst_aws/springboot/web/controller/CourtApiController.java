package com.bst_aws.springboot.web.controller;

import com.bst_aws.springboot.service.court.CourtService;
import com.bst_aws.springboot.web.dto.request.CourtSaveRequestDto;
import com.bst_aws.springboot.web.dto.response.CourtListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CourtApiController {

    private final CourtService courtService;

    @PostMapping("/api/v2/court")
    public Long save(@RequestBody CourtSaveRequestDto requestDto){
        return courtService.save(requestDto);
    }

    @GetMapping("/api/v2/court/list")
    public List<CourtListResponseDto> findAllAsc(){
        return courtService.findAllAsc();
    }
}

package com.bst_aws.springboot.web.controller;

import com.bst_aws.springboot.service.vcount.VCountService;
import com.bst_aws.springboot.web.dto.request.VCountSaveRequestDto;
import com.bst_aws.springboot.web.dto.request.VCountUpdateRequestDto;
import com.bst_aws.springboot.web.dto.response.VCountListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class VCountApiController {
    private final VCountService vCountService;

    @PostMapping("/api/v2/vcount")
    public Long save (@RequestBody VCountSaveRequestDto requestDto){
        return vCountService.save(requestDto);
    }

    @PutMapping("/api/v2/vcount/{id}")
    public Long update(@PathVariable Long id, @RequestBody VCountUpdateRequestDto requestDto){
        return vCountService.update(id,requestDto);
    }

    @GetMapping("/api/v2/vcount/list")
    public List<VCountListResponseDto> findAllDesc(){
        return vCountService.findAllDesc();
    }

}

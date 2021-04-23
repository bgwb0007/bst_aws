package com.bst_aws.springboot.service.vcount;

import com.bst_aws.springboot.config.auth.dto.OAuthAttributes;
import com.bst_aws.springboot.config.auth.dto.SessionUser;
import com.bst_aws.springboot.domain.user.User;
import com.bst_aws.springboot.domain.vcount.VCount;
import com.bst_aws.springboot.domain.vcount.VCountRepository;
import com.bst_aws.springboot.web.dto.request.VCountSaveRequestDto;
import com.bst_aws.springboot.web.dto.request.VCountUpdateRequestDto;
import com.bst_aws.springboot.web.dto.response.VCountListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VCountService {
    private VCountRepository vCountRepository;


    // 방문기록_ 없으면 생성, 있으면 count +1
    public Long saveOrUpdate(VCountSaveRequestDto requestDto) {
        VCount vCount = new VCount();
        if (vCountRepository
                .existsByVisitedDateContainingAndUserEmail(
                        LocalDate.now().toString(),
                        requestDto.getUserEmail())){
            vCount = vCountRepository.findByVisitedDateContainingAndUserEmail(LocalDate.now().toString(), requestDto.getUserEmail())
                    .map(entity -> entity.visited_check())
                    .orElse(requestDto.toEntity());
        }else {
            vCount = requestDto.toEntity();
        }
        return vCountRepository.save(vCount).getId();
    }

    //save
    public Long save(VCountSaveRequestDto requestDto){
        return vCountRepository.save(requestDto.toEntity()).getId();
    }
    //update
    public Long update(Long id, VCountUpdateRequestDto requestDto){
        VCount vCount = vCountRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 방문기록이 없습니다. id="+id));
        vCountRepository.delete(vCount);
        return id;
    }
    //read
    public List<VCountListResponseDto> findAllDesc(){
        return vCountRepository.findAllDesc().stream()
                .map(VCountListResponseDto::new)
                .collect(Collectors.toList());
    }

}

package com.bst_aws.springboot.domain.vcount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VCountRepository extends JpaRepository<VCount, Long> {
    // 방문자수 전체 목록 조회
    @Query("SELECT vc from VCount vc order by vc.createdDate desc")
    List<VCount> findAllDesc();

    Optional<VCount> findByVisitedDateContainingAndUserEmail(String visitedDate, String userEmail);

    // 오늘 방문기록이 이미 있는지 확인 >> 있으면 카운트만 +1할거임
    Boolean existsByVisitedDateContainingAndUserEmail(String visitedDate, String userEmail);
}

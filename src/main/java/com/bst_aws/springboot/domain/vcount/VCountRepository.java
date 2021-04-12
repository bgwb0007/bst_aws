package com.bst_aws.springboot.domain.vcount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VCountRepository extends JpaRepository<VCount, Long> {
    Optional<VCount> findByCreatedDate(LocalDateTime createdDate);

    @Query("SELECT vc from VCount vc order by vc.createdDate desc")
    List<VCount> findAllDesc();
}

package com.bst_aws.springboot.domain.visitedCount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface VisitedCountRepository extends JpaRepository<VisitedCount, Long> {
    Optional<VisitedCount> findByCreatedDate(LocalDateTime createdDate);
}

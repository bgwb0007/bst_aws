package com.bst_aws.springboot.domain.court;

import com.bst_aws.springboot.domain.lesson.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourtRepository extends JpaRepository<Court, Long> {
    List<Court> findAllByDistrict(String district);

    @Query("SELECT c from Court c order by c.name asc")
    List<Court> findAllAsc();
}

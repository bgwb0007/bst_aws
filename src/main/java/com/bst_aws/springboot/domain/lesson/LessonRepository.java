package com.bst_aws.springboot.domain.lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select district  from Lesson group by district order by district asc")
    List<String> findAllDistrictList();

    @Query("SELECT l from Lesson l order by l.name asc")
    List<Lesson> findAllAsc();
}

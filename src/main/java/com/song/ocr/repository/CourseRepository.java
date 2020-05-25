package com.song.ocr.repository;

import com.song.ocr.domain.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends MongoRepository<Course, Long> {

    @Query("{'skill' : ?0 }")
    List<Course> findBySkill(String skill);
}

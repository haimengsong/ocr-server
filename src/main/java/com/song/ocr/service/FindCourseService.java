package com.song.ocr.service;

import com.song.ocr.domain.CourseDTO;

import java.util.List;

public interface FindCourseService {
    List<CourseDTO> findCourse(String jobDescription);
}

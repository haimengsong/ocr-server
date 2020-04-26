package com.song.ocr.service;

import com.song.ocr.domain.Course;

import java.util.List;

public interface FindCourseService {
    List<Course> findCourse(String jobDescription);
}

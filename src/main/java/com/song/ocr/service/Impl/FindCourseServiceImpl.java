package com.song.ocr.service.Impl;

import com.song.ocr.domain.Course;
import com.song.ocr.extractor.JobDescriptionExtractor;
import com.song.ocr.service.FindCourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class FindCourseServiceImpl implements FindCourseService {

    @Autowired
    private JobDescriptionExtractor jobDescriptionExtractor;

    @Override
    public List<Course> findCourse(String jobDescription) {

        Map<String, Integer> skills = jobDescriptionExtractor.extract(jobDescription);

        return null;
    }
}

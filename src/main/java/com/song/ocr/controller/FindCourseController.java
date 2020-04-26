package com.song.ocr.controller;


import com.song.ocr.domain.Course;
import com.song.ocr.service.FindCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FindCourseController {

    @Autowired
    private FindCourseService findCourseService;

    @GetMapping()
    public List<Course> findCourse(@RequestBody String jobDescription) {
        return findCourseService.findCourse(jobDescription);
    }
}

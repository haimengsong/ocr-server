package com.song.ocr.controller;


import com.song.ocr.domain.Course;
import com.song.ocr.service.FindCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FindCourseController {

    @Autowired
    private FindCourseService findCourseService;

    @CrossOrigin
    @RequestMapping(value = "courses", method = RequestMethod.POST)
    public List<Course> findCourse(@RequestBody String jobDescription) {
        return findCourseService.findCourse(jobDescription);
    }
}

package com.song.ocr.service.Impl;

import com.song.ocr.domain.Course;
import com.song.ocr.domain.CourseDTO;
import com.song.ocr.extractor.JobDescriptionExtractor;
import com.song.ocr.repository.CourseRepository;
import com.song.ocr.service.FindCourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class FindCourseServiceImpl implements FindCourseService {

    private static final int N = 10;

    @Autowired
    private JobDescriptionExtractor jobDescriptionExtractor;

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public List<CourseDTO> findCourse(String jobDescription) {

        Map<String, Integer> skills = jobDescriptionExtractor.extract(jobDescription);

        List<String> sortedSkillsByFrequency = getSkillsByDecreasingFrequency(skills);

        List<CourseDTO> courses = new ArrayList<>();

        sortedSkillsByFrequency.forEach(skill -> {
            courses.addAll(findTopNBestCourses(courseRepository.findBySkill(skill), N));
        });

        return courses;
    }

    private List<CourseDTO> findTopNBestCourses(List<Course> courses, int n) {
        courses.sort(Comparator.comparing(Course::getScore));

        List<CourseDTO> courseDTOS = new ArrayList<>();

        int count = 0;

        while(count < n && count < courses.size()) {

            Course course = courses.get(count);

            CourseDTO courseDTO = new CourseDTO();

            courseDTO.setSkill(course.getSkill());
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setDescription(course.getDescription());
            courseDTO.setId(course.getId());
            courseDTO.setEnrollmentNum(course.getEnrollmentNum());
            courseDTO.setLevel(course.getLevel());
            courseDTO.setPrice(course.getPrice());
            courseDTO.setScore(course.getScore());
            courseDTO.setUrl(course.getUrl());

            courseDTOS.add(courseDTO);

            count++;
        }

        return courseDTOS;
    }

    private List<String> getSkillsByDecreasingFrequency(Map<String, Integer> skills) {

        int maxFrequency = getMaxFrequency(skills);

        List<String> sortedSkills = new ArrayList<>();

        if(maxFrequency == 0) {
            return sortedSkills;
        }

        List<String> [] bucket = new List[maxFrequency + 2];

        skills.forEach((k,v) -> {
            if(bucket[v] == null) {
                bucket[v] = new ArrayList<>();
            }

            bucket[v].add(k);
        });

        for(int i = maxFrequency + 1; i > 0; i--) {

            if(bucket[i] != null) {
                sortedSkills.addAll(bucket[i]);
            }
        }

        return sortedSkills;
    }

    private int getMaxFrequency(Map<String, Integer> skills) {
        final int[] max = {0};
        skills.forEach((k, v) -> {
            if(v > max[0]) {
                max[0] = v;
            }
        });
        return max[0];
    }
}

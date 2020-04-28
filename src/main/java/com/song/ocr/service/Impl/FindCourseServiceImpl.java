package com.song.ocr.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.song.ocr.domain.Course;
import com.song.ocr.elasticsearch.SearchResult;
import com.song.ocr.elasticsearch.SearchService;
import com.song.ocr.extractor.JobDescriptionExtractor;
import com.song.ocr.service.FindCourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindCourseServiceImpl implements FindCourseService {


    private static final String COURSE_INDEX = "course";
    private static final String SKILL_FIELD = "skills";

    @Autowired
    private JobDescriptionExtractor jobDescriptionExtractor;

    @Autowired
    private SearchService searchService;


    @Override
    public List<Course> findCourse(String jobDescription) {

        Map<String, Integer> skills = jobDescriptionExtractor.extract(jobDescription);

        List<String> sortedSkillsByFrequency = getSkillsByDecreasingFrequency(skills);

        List<Course> courses = sortedSkillsByFrequency.stream().flatMap(skill -> searchService.search(COURSE_INDEX, SKILL_FIELD, skill)
                .getResultDocument().stream()).map(searchResult -> {


                    ObjectMapper mapper = new ObjectMapper();

                    Course course = mapper.convertValue(searchResult.getDocument(), Course.class);

                    return course;
        }).collect(Collectors.toList());

        return courses;
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

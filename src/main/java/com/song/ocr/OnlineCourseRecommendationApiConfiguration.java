package com.song.ocr;

import com.song.ocr.extractor.JobDescriptionExtractor;
import com.song.ocr.service.FindCourseService;
import com.song.ocr.service.Impl.FindCourseServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OnlineCourseRecommendationApiConfiguration {

    @Bean
    public FindCourseService findCourseService(){
        return new FindCourseServiceImpl();
    }

    @Bean
    public JobDescriptionExtractor jobDescriptionExtractor(){
        return new JobDescriptionExtractor();
    }
}

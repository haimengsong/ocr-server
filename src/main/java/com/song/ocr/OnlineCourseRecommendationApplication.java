package com.song.ocr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;

@SpringBootApplication
public class OnlineCourseRecommendationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineCourseRecommendationApplication.class, args);
	}

}

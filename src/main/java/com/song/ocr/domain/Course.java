package com.song.ocr.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Course {
    private Long id;
    private String skill;
    private String courseName;
    private String description;
    private String url;
    private Double score;
    private String enrollmentNum;
    private String level;
    private Double price;
    private String source;
}

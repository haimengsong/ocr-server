package com.song.ocr.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {
    private long id;
    private String skill;
    private String courseName;
    private String description;
    private String enrollmentNum;
    private double score;
    private String level;
    private double price;
    private String url;
}

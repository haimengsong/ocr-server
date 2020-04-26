package com.song.ocr.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Course {
    private long id;
    private String courseName;
    private String description;
    private String url;
    private String skills;
    private double score;
    private String enrollmentNum;
    private String level;
    private double price;
    private String source;
}

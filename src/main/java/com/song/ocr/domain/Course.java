package com.song.ocr.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Getter
@Setter
public class Course implements Serializable {
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

    @Override
    public String toString(){
        return "[id=" + id +
                " ,skill=" + skill +
                " ,courseName=" + courseName +
                " ,description=" + description +
                " ,url=" + url +
                " ,score=" + score +
                " ,enrollmentNum=" + enrollmentNum +
                " ,level=" + level +
                " ,price=" + price +
                " ,source=" + source +
                "]";
    }
}

package com.song.ocr.redis;

import com.song.ocr.domain.Course;
import com.song.ocr.util.SerializeObjectTool;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.stream.Collectors;

public class RedisClient {


    public static void main(String [] args) {

        Jedis redis = new Jedis("127.0.0.1", 6379);

        List<Course> courses = new ArrayList<>();

        for(int i = 0; i < 20; i++) {
            Course course = new Course();
            course.setCourseName("Java" + i);
            Random random = new Random();
            course.setScore(random.nextInt(50)/10.0);
            course.setDescription("description" + i);
            course.setEnrollmentNum(random.nextInt(20) + "k");
            course.setLevel("entry");
            course.setUrl("www.coursera.com");
            courses.add(course);
        }


        Map<String, Double> map = new HashMap<>(courses.size() * 2);

        courses.forEach(course -> {

            map.put(new String(SerializeObjectTool.serialize(course)), course.getScore());
        });

        if(redis.exists("java")) {
            redis.del("java");
        }

        redis.zadd("java", map);


        Set<String> values = redis.zrevrange("java", 0, 9);

        List<Course> top10 = values.stream().map(v -> (Course)SerializeObjectTool.unserialize(v.getBytes())).collect(Collectors.toList());


        top10.forEach(course -> {
            System.out.println(course.toString());
        });
    }


}

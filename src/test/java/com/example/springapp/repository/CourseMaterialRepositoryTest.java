package com.example.springapp.repository;

import com.example.springapp.entity.Course;
import com.example.springapp.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    @Test
    void saveCourseMaterial(){
        Course course = Course.builder()
                .title("DSA")
                .credit(6)
                .build();
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("google.com")
                .course(course)
                .build();

        repository.save(courseMaterial);
    }

    @Test
    void listAllCourseMaterial(){
        List<CourseMaterial> courseMaterialList = repository.findAll();
        System.out.println("courseMaterialList = " + courseMaterialList);
    }

}
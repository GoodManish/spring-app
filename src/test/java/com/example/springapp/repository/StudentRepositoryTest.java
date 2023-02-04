package com.example.springapp.repository;

import com.example.springapp.entity.Guardian;
import com.example.springapp.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

//    @Test
    void saveStudent() {
        Guardian guardian = Guardian.builder()
                .name("Ram")
                .mobile("99000099990")
                .email("RAM@gmail.com")
                .build();
        Student student = Student.builder()
                .emailId("son@gmail.com")
                .firstName("son")
                .lastName("singh")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    void getAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }

    @Test
    void getStudentByName() {
        List<Student> studentList = studentRepository.findByFirstName("van");
        System.out.println("studentList = " + studentList);
    }

    @Test
    void getStudentByNameContain() {
        List<Student> studentList = studentRepository.findByFirstNameContains("va");
        System.out.println("studentList = " + studentList);
        assertNotNull(studentList);

    }
    @Test
    void test_findByFirstNameOrderByGuardian_EmailAsc() {
        List<Student> studentList = studentRepository.findByFirstNameOrderByGuardianEmailAsc("van");
        System.out.println("studentList = " + studentList);
        assertNotNull(studentList);

    }

    @Test
    void findByGuardian_Name() {
        List<Student> guardianName = studentRepository.findByGuardianName("Updated-Guardian-Name");
        System.out.println("ram = " + guardianName);
        assertEquals("son", guardianName.get(0).getFirstName());
    }

    @Test
    void findStudentByEmailAddress() {
        Student student = studentRepository.findStudentByEmailAddress("son@gmail.com");
        System.out.println("student = " + student);
    }
    @Test
    void findFirstNameByEmailAddress() {
        String name = studentRepository.findFirstNameByEmailAddress("son@gmail.com");
        System.out.println("Name = " + name);
    }

    @Test
    void findGuardianEmailByStudentEmailAddressTest() {
        String guardianName = studentRepository.findGuardianEmailByStudentEmailAddress("son@gmail.com");
        System.out.println("Name = " + guardianName);
    }

    @Test
    void findStudentByStudentEmail() {
        Student student = studentRepository.findStudentByStudentEmail_NativeQuery("son@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    void update_StudentGuardianName_By_StudentEmail_Query_update_query() {
        int updatedRows = studentRepository.updateStudentGuardianNameByStudentEmail_Jpql_Query("Updated-Guardian-Name", "son@gmail.com");
        System.out.println("student updated rows: = " + updatedRows);
    }
}
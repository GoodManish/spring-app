package com.example.springapp.repository;

import com.example.springapp.entity.Student;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);
    List<Student> findByFirstNameContains(String firstName);
    List<Student> findByFirstNameOrderByGuardianEmailAsc(String firstName);
    List<Student> findByGuardianName(String name);

//  @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select s from Student s where s.emailId=:email")
    Student findStudentByEmailAddress(String email);
    @Query("select s.firstName from Student s where s.emailId=:email")
    String findFirstNameByEmailAddress(String email);
    @Query("select s.guardian.email from Student s where s.emailId=:email")
    String findGuardianEmailByStudentEmailAddress(String email);
    @Query(value = "select * from tbl_student s where s.email_address = :emailid", nativeQuery = true)
    Student findStudentByStudentEmail_NativeQuery(@Param("emailid") String email);

    @Transactional
    @Modifying
    @Query(value = "update tbl_student set guardian_name=:name where email_address=:email", nativeQuery = true)
    int update_StudentGuardianName_By_StudentEmail_Native_Query(@Param("name") String name,
                                                                @Param("email") String email);
    @Transactional
    @Modifying
//    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("update Student s set s.guardian.name=:name where s.emailId=:email")
    int updateStudentGuardianNameByStudentEmail_Jpql_Query(@Param("name") String name,
                                                           @Param("email") String email);










}

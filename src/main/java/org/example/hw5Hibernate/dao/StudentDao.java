package org.example.hw5Hibernate.dao;

import org.example.hw5Hibernate.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {

    List<Student> findAll();
    Optional<Student> findStudentById(Long id);
    void saveOrUpdateStudent(Student newStudent);
    void savaAllStudents(List<Student> students);
    void deleteStudentById(Long id);
    void deleteStudent(Student student);
}

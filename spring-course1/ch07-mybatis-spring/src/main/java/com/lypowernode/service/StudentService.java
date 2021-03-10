package com.lypowernode.service;

import com.lypowernode.domain.Student;

import java.util.List;

public interface StudentService {

    int addStudent(Student student);

    List<Student> queryStudents();
}

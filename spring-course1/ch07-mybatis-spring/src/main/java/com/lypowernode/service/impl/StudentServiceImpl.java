package com.lypowernode.service.impl;

import com.lypowernode.dao.StudentDao;
import com.lypowernode.domain.Student;
import com.lypowernode.domain.StudentExample;
import com.lypowernode.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    // 引用类型
    private StudentDao studentDao;

    // 使用set注入 , 赋值

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int addStudent(Student student) {
        int nums = studentDao.insert(student);
        return nums;
    }

    @Override
    public List<Student> queryStudents() {
        StudentExample example = new StudentExample();
        List<Student> students = studentDao.selectByExample(example);
        return students;
    }
}

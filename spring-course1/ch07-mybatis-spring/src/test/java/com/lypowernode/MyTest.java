package com.lypowernode;


import com.lypowernode.dao.StudentDao;
import com.lypowernode.domain.Student;
import com.lypowernode.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class MyTest {
    @Test
    public void test01(){

        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        String[] names = ac.getBeanDefinitionNames();
        for (String na : names){
            System.out.println("容器中对象的名称:"+na);
        }
    }

    @Test
    public void testDaoInsert(){

        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        StudentDao dao = (StudentDao) ac.getBean("studentDao");
        Student student = new Student();
        student.setId(202101);
        student.setName("生悟");
        student.setEmail("123456@163.com");
        student.setAge(29);
        int nums = dao.insert(student);
        System.out.println(nums);
    }

    @Test
    public void tsetServiceInsert() {

        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        StudentService service = (StudentService) ac.getBean("studentService");
        Student stu= (Student) ac.getBean("myStudent");



        int nums = service.addStudent(stu);
        System.out.println(nums);
    }

    @Test
    public void tsetServiceSelect() {

        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        StudentService service = (StudentService) ac.getBean("studentService");
        List<Student> students = service.queryStudents();
        for (Student stu:students){
            System.out.println(stu);
        }
    }
}



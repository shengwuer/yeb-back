package com.lypowernode;

import com.lypowernode.ba06.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytest06 {

    @Test
    public void test01(){
        String config = "ba06/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

       Student student = (Student) ac.getBean("student");
        System.out.println(student);

    }
}

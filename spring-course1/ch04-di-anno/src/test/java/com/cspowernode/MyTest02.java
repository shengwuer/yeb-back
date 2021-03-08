package com.cspowernode;

import com.cspowernode.ba08.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest02 {
    @Test
    public void test01(){
String config="applicationContextValue.xml";
        ApplicationContext ac=new ClassPathXmlApplicationContext(config);
      Student stu= (Student) ac.getBean("myStudent");
        System.out.println(stu);
    }
}

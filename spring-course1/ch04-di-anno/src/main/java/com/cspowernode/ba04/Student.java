package com.cspowernode.ba04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "myStudent")//也可以这样格式：@Component（ "myStudent")或者@Component
public class Student {
    @Value(value="张飞")//也可以这样格式：@Value(value="张飞")
    private String name;
    @Value(value="38") //也可以这样格式：@Value("38")
    private Integer age;


    /*
    * 引用类型
    * @Autowired：spring框架提供的注解，实现引用类型的赋值。
    * spring中通过注解给引用类型赋值，使用的式自动注入原理，支持byName，bType
    * @Autowired:默认使用的式不要Type
    * */


@Autowired
@Qualifier("mySchool")
    private School school;

    public Student() {
    }

    public Student(String name, Integer age, School school) {
        this.name = name;
        this.age = age;
        this.school = school;
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    /*
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }*/

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school +
                '}';
    }
}



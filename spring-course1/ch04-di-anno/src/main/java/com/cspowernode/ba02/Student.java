package com.cspowernode.ba02;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "myStudent")//也可以这样格式：@Component（ "myStudent")或者@Component
public class Student {
    @Value(value="张飞")//也可以这样格式：@Value(value="张飞")
    private String name;
    @Value(value="38") //也可以这样格式：@Value("38")
    private Integer age;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    /*public void setName(String name) {
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
                '}';
    }
}



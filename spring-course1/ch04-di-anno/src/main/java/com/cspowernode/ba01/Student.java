package com.cspowernode.ba01;

import org.springframework.stereotype.Component;


/*
@Component(value = "myStudent")等同于
<bean id="myStudent" class=“com.cspowernode.ba01”
*/

@Component(value = "myStudent")//也可以是这样格式：@Component("myStudent")或者是@Component但是测试类的对象名必须是类Student的首字母小写
public class Student {
    private String name;
    private Integer age;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}



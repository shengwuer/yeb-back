package com.cspowernode.ba02;

public class Student {
    private String name;
    private Integer age;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName(String xiedi) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge(int i) {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

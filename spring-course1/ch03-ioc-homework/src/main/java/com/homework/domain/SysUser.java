package com.homework.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component(value = "mySysUser")
public class SysUser {
    @Value("生悟")
    private String name;
    @Value("29")
    public  Integer age;

    public SysUser() {
    }

    public SysUser(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName(String lisi) {
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

    @Override
    public String toString() {
        return "SysUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

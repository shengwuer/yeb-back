package com.lypowernode.ba06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Student {

    /*
     * @Value ： 简单类型的属性赋值
     *    属性 ： value 是String类型的 ， 表示简单类型的属性值
     *    位置 ： 1. 在属性定义的上面 ，无需set方法 ， 推荐使用。
     *           2. 在set方法上面
     * */
    @Value(value = "武元甲")
    private String name;
    // value也可以省略
    @Value("29")
    private int age;

/*      引用类型 :
        @Resource : 来自jdk的注解 , spring框架提供了对这个注解的功能支持 , 可以使用他给引用类型赋值
                    使用的也是自动注入原理 , 支持byName ,byType ,默认是byName

         位置 :　1. 在属性的定义上面 ,无需set方法 , 推荐使用
                2. 在set方法的上面也可以
 */

    // @Resource的byName原则 : 先使用byName自动注入 ,如果byName赋值失败 , 再使用byType
    @Resource
    private School school;


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school +
                '}';
    }
}

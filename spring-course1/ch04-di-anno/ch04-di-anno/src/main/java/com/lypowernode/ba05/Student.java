package com.lypowernode.ba05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {

    /*
     * @Value ： 简单类型的属性赋值
     *    属性 ： value 是String类型的 ， 表示简单类型的属性值
     *    位置 ： 1. 在属性定义的上面 ，无需set方法 ， 推荐使用。
     *           2. 在set方法上面
     * */
    @Value(value = "朱德")
    private String name;
    // value也可以省略
    @Value("29")
    private int age;

/*     * 引用类型 :
     * @Autowired ： spring框架提供的注解 , 实现 引用类型的赋值
                    spring中通过注解给引用类型赋值 , 使用的是自动注入原理 , 支持byName ,byType

       @Autowired :默认使用的是byType自动注入
       *  属性 : required , 是一个boolean类型的默认true
       *        required=true ： 表示引用类型赋值失败 ，程序报错 ， 并终止执行
       *        required=false ： 表示如果引用类型赋值失败 ，程序正常执行 ,引用类型输出值是null
       *
     *    位置 ： 1. 在属性定义的上面 ，无需set方法 ， 推荐使用。
     *           2. 在set方法上面
     *
     *  如果使用byName方式 , 需要做的是 :
     *      1. 在属性上面加入 @Autowired
     *      2. 在属性上面加入 @Qualifier(value="bean的id") : 表示使用指定名称的bean完成赋值
     *
     *
     * */
    // byName 自动注入

    /*
     *    required=true ： 表示引用类型赋值失败 ，程序报错 ， 并终止执行
    *     required=false ： 表示如果引用类型赋值失败 ，程序正常执行 ,引用类型输出值是nul
    * */
    @Autowired(required = false)
    // @Qualifier(value="bean的id")  value也可以省略
    @Qualifier("mySchool")
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

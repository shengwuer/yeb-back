package com.lypowernode.ba01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* @Component : 创建对象的 , 等同于<bean>的功能
*           属性 : value 就是对象名称 ,也就是bean的id值,
*                 value的值是唯一的 , 创建对象在spring容器中就一个
*           位置 : 在类的上面
* `
* @Component(value = "myStudent")等同于<bean id="myStudent" class="com.lypowernode.ba01.Student"/>
*
*
* spring中和@Component功能一致 ， 创建对象的注解还有 ：
*   1. @Repository(用在持久层类的上面) ； 放在dao的实现类上面，
*                   表示创建dao对象是能够访问数据库的。
*   2. @Service（用在业务层类的上面） ： 放在service对象的实现类上面，
*                   创建service对象， service对象是做业务处理 ， 可以有事务功能的。
*   3. @Controller（用在控制器上面） ： 放在控制器（处理器）类的上面 ， 创建控制器对象的 ，
*                   控制器对象 ，能够接受用户提交的参数 ， 显示请求的处理结果。
*   以上三个注解的使用语法和@Component一样的。 都能创建对象 ， 但是还有额外的功能
*   @Repository，@Service，@Controller 他们是给项目的对象分层的
* */
/*

@Component(value = "myStudent")
*/
/*

// 省略value
@Component( "myStudent")
*/

// 不指定对象 ， spring提供id对象的默认名称 ：是类名首字小写
@Component
public class Student {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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

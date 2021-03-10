package com.homework;

import com.homework.domain.SysUser;
import com.homework.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApp {
    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
      UserService service= (UserService) ac.getBean("userService");

        SysUser user=new SysUser();
        user.getName("ksk");
        user.getAge(29);
        service.addUser(user);
        System.out.println(user);
    }
}

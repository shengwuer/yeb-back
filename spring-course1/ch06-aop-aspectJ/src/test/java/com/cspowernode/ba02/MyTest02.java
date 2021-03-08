package com.cspowernode.ba02;

import com.cspowernode.ba02.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest02 {
    @Test
    public void test01() {
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        SomeService proxy = (SomeService) ac.getBean("someService");
        System.out.println(proxy.getClass().getName());
       ;
        proxy.doOther ("3d",12);
        String str = proxy.doOther("张",31);
        System.out.println("str:" + str );



    }
}

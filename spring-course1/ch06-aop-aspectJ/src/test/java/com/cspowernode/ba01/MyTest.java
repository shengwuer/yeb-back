package com.cspowernode.ba01;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test01() {
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        SomeService proxy = (SomeService) ac.getBean("someService");
        SomeService proxy1 = (SomeService) ac.getBean("someService1");

        // com.sun.proxy.$Proxy8: jdk动态代理
        System.out.println("porxy:"+proxy.getClass().getName());
        proxy.doOther("李四",20);
        proxy.doSome("张三",22);
        proxy1.doOther("李wu",20);
        proxy1.doSome("生悟",29);
    }
}

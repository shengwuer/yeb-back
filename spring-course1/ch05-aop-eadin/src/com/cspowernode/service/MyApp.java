package com.cspowernode.service;

import com.cspowernode.handler.MyIncationHandler;
import com.cspowernode.service.impl.SomeServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MyApp {
    public static void main(String[] args) {
       /* //调用doSome,doOther
        SomeService service=new SomeServiceImpl();
        service.doSome();
        System.out.println("==========");
        service.doOther();*/
        // 使用jdk的proxy创建代理对象
        // 创建目标对象
        SomeService target=new SomeServiceImpl();

        // 创建InvocationHandler对象
        InvocationHandler hangler=new MyIncationHandler(target);

        // 使用Proxy创建代理
       SomeService proxy= (SomeService) Proxy.newProxyInstance(
               target.getClass().getClassLoader(),
               target.getClass().getInterfaces(),hangler);
        System.out.println("proxy:"+proxy.getClass().getName());

       // 通过代理执行方法结果
        proxy.doSome();
        System.out.println("===========");
        proxy.doOther();
    }
}

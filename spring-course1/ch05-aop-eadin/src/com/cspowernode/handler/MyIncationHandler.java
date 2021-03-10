package com.cspowernode.handler;

import com.cspowernode.util.ServiceTools;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyIncationHandler implements InvocationHandler {
    private Object target;
        //  目标对象
    public MyIncationHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        // 通过代理对象执行方法时,会调用执行这个invoke()

        System.out.println("执行MyIncationHandler中的invoke()");
        System.out.println("method名称:"+method.getName());
        String methodName= method.getName();
        Object res=null;
        if ("doSome".equals(methodName)){
            ServiceTools.doLog();
            // 执行目标类的方法,通过method类实现
            res  =method.invoke(target, objects); //这是SomeServiceImpl.doOther().doSome()
            ServiceTools.doTrans ();
        } else {
            res =method.invoke (target,objects);
        }


       // 目标方法的执行结果
        return res;
    }
}

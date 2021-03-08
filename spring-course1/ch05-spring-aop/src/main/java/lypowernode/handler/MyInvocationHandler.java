package lypowernode.handler;

import lypowernode.util.ServiceTools;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    Object target; // 最终会是SomeServiceImpl

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 通过代理对象执行方法时 , 会调用这个invoke()
         Object res =null;
        System.out.println("执行MyInvocationHandler中的invoke()方法");
        // 用Method类的对象method嗲用getName方法获取执行方法的名称
        System.out.println("methodm名称 : "+method.getName());
        // 做一个变量得到执行方法的名称字符串
        String doSomeMethod= method.getName();
        // 取得执行方法的名称的字符串来控制是否给业务方法增加非业务方法
  /*      if (("doSome").equals(doSomeMethod)){
            ServiceTools.dolog();
            // 执行目标类的方法 , 通过Method类实现
            res = method.invoke(target, args);// 这是代表 ： SomeServiceImpl.doSome()
            ServiceTools.doTrans();
        }else{
            res = method.invoke(target, args);// 这是代表 ：SomeServiceImpl.doSome(),doOther()
        }*/

        ServiceTools.dolog();
        // 执行目标类的方法 , 通过Method类实现
         res = method.invoke(target, args);// 这是代表 ： SomeServiceImpl.doSome(),doOther()
        ServiceTools.doTrans();
        return res;
    }
}

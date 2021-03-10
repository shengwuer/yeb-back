package lypowernode;

import lypowernode.handler.MyInvocationHandler;
import lypowernode.service.OtherService;
import lypowernode.service.SomeService;
import lypowernode.service.impl.OtherServiceImpl;
import lypowernode.service.impl.SomeServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MyApp {
    public static void main(String[] args) {

         // 使用jdk ， 创建动态代理对象
        // 创建目标对象
       /* SomeService target = new SomeServiceImpl();

        // 创建InovcationHandler对象
        InvocationHandler handler = new MyInvocationHandler(target);

        // 使用Proxy创建代理 newProxyInstance是目标类SomeServiceImpl的类加载器 然后用目标类SomeServiceImpl的对象target的反射机制来获取加载
       SomeService proxy = (SomeService) Proxy.newProxyInstance(
                                // target.getClass().getClassLoader()表示用目标类的对象target的反射机制获取目标类
                                target.getClass().getClassLoader(),
                                // target.getClass().getInterfaces()表示 : SomeServiceImpl类对象target 实现了那些接口
                                target.getClass().getInterfaces(),
               handler);
       // 通过代理执行方法 , 调用handler中invoke()
        proxy.doSome();

        System.out.println("==============================");
        proxy.doOther();

        // 动态代理 : com.sun.proxy.$Proxy0
        System.out.println("动态代理 : "+proxy.getClass().getName());
*/

        // 创建对象
        OtherService service = new OtherServiceImpl();

        InvocationHandler handler =new MyInvocationHandler(service);

      OtherService proxy = (OtherService) Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(),handler);
      proxy.first();
        System.out.println("===============================");
        System.out.println();
        proxy.second();
    }
}

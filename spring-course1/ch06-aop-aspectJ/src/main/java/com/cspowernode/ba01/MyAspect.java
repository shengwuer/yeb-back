package com.cspowernode.ba01;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import sun.awt.SunHints;

import java.awt.*;
import java.util.Date;

/*
*  @Aspect: 式 aspectJ框架中的注解.
*      作用: 表示当前类是切面类*
*     切面类:是用来给当前业务方法增加功能的类,在这个类中有切面的功能代码
*      位置: 在类定义的上面
* * * */
@Aspect
public class MyAspect {
    /*
    *  定义方法,方法是实现切面功能的.
    *  方法定义要求:
    *    1. 公共方法public;
    *    2. 方法没有返回值;
    *    3. 方法名称自定义;
    *    4. 方法可以有参数,也可以没参数,
    *       如果有参数,参数不是自定义的,有几个参数类型可以使用.
    *
    * */
    // SomeServiceImpl是目标类所以配置文件applicationContext.xml里的id="someService"对象是代理对象
/*@Before(value="execution(public void com.cspowernode.ba01.SomeServiceImpl.doSome(String,Integer))")
public void myBefore(){
        System.out.println("前置通知, 在切面之前执行时间方法:"+new Date());
    }*/


/*   @Before(value="execution(void com.cspowernode.ba01.SomeServiceImpl.doSome(String,Integer))")
    public void myBefore(){
        System.out.println("1前置通知, 在切面之前执行时间方法:"+new Date());
    }*/

/*
 // (value="execution(void *..SomeServiceImpl.doSome(String,Integer))")省略权限修饰,包
@Before(value="execution(void *..cspowernode.ba01.SomeServiceImpl.doSome(String,Integer))")
public void myBefore(){
    System.out.println("2前置通知, 在切面之前执行时间方法:"+new Date());
    }*/

/*
    // (value="execution(void *..SomeServiceImpl.doSome(..))")省略权限修饰,包,参数
@Before(value="execution(void *..SomeServiceImpl.doSome(String,Integer))")
public void myBefore(){
    System.out.println("2-1前置通知, 在切面之前执行时间方法:"+new Date());
}*/

/*
  // (value="execution(* *..SomeServiceImpl.doSome(..))")省略权限修饰,返回值,包,参数
@Before(value="execution(* *..SomeServiceImpl.doSome(..))")
public void myBefore(){
    System.out.println("3前置通知, 在切面之前执行时间方法:"+new Date());
}
*/
/*
    // (value="execution(* *..SomeServiceImpl.do*(..))")省略权限修饰,返回值,包,do*(目标类中所有do开头的方法),参数
@Before(value="execution(* *..SomeServiceImpl.do*(..))")
public void myBefore(){
    System.out.println("3前置通知, 在切面之前执行时间方法:"+new Date());
}  */
/*

// (value="execution(* *..SomeServiceImpl.do*(..))")省略权限修饰,返回值,包,*(目标类中所有方法),参数
@Before(value="execution(* *..SomeServiceImpl.do*(..))")
public void myBefore(){
    System.out.println("3前置通知, 在切面之前执行时间方法:"+new Date());
}

@Before(value="execution(* *..SomeServiceImpl.do*(..))")
public void myBefore1(){
    System.out.println("3前置通知, 在切面之前执行时间方法:"+new Date());
}

@After (value="execution(* *..SomeServiceImpl.do*(..))")
public void myAfter(){
    System.out.println("4后置通知, 在切面之前执行时间方法:"+new Date());
}
*/

/*
@Before(value="execution(void *..SomeServiceImpl.doSome())") // 参数如果错了就找不到代理就会输出这个目标类:SomeServiceImpl
public void myBefore(){
    System.out.println("5前置通知, 在切面之前执行时间方法:"+new Date());
}
*/

    @Before(value="execution(void com.cspowernode.ba01.*.*(..))") // 参数如果错了就找不到代理就会输出这个目标类:SomeServiceImpl
    public void myBefore(JoinPoint jp){
        // 获取方法的完整定义
        System.out.println("方法的签名(定义):"+jp.getSignature());
        System.out.println("方法的名称:"+jp.getSignature().getName());
        // 获取方法的实参
        Object[] args = jp.getArgs();
        for (Object arg:args){
            System.out.println(arg);
        }
        System.out.println("5前置通知, 在切面之前执行时间方法:"+new Date());
    }
}

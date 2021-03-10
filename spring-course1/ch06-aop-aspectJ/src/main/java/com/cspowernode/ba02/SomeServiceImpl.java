package com.cspowernode.ba02;

/**
 * 目标类
 */
public class SomeServiceImpl implements SomeService {

    @Override
    public void doSome(String name, Integer age) {
        // 给doSome()方法增加一个功能,在doSome()执行之前,输出方法的执行时间
        System.out.println("===目标方法==");
    }
    @Override
    public String doOther(String name, Integer age) {
        // 给doSome()方法增加一个功能,在doSome()执行之前,输出方法的执行时间
        System.out.println("===目标方法doOther()==");
     return "adsf";
    }

    @Override
    public Student doOther2(String name, Integer age) {
        Student student=new Student();
        student.setName("xiedi");
        student.setAge(20);
        return student;
    }


    //切面类
}

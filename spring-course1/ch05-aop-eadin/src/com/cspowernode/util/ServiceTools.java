package com.cspowernode.util;

import java.util.Date;

public class ServiceTools {
    public static void doLog() {
        System.out.println("doSome执行时间:" + new Date());
    }

    public static void doTrans() {
        System.out.println("方法执行完毕后,提交事务");

    }
}



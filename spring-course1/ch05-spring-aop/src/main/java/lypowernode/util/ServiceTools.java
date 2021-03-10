package lypowernode.util;

import java.util.Date;

public class ServiceTools {



   public static void dolog(){
        System.out.println("非业务方法,时间 : "+ new Date());
        
    }
    public static void doTrans (){

        System.out.println("事务完成提交事务!!");
    }
}

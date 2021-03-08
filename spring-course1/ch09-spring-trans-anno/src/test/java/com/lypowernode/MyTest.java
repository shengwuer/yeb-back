package com.lypowernode;

import com.lypowernode.service.BuyGoodsService;
import javafx.application.Application;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void test(){
        String config="applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        BuyGoodsService buyService = (BuyGoodsService) ac.getBean("buyGoodsService");
        // com.sun.proxy.$Proxy16
        System.out.println("buyService是代理 : "+buyService.getClass().getName()+"\n" );
        // 增补旧货数量
        buyService.addGoods(1002,101);
        buyService.addGoods(1003,101);

         // 卖出去的
        buyService.buy(1001,10);
        buyService.buy(1002,20);
        buyService.buy(1003,10);

        /*
        // 增补新货
        buyService.addNewGoods(1005,"电视机",100,1500);
        buyService.addNewGoods(1004,"洗衣机",100,1200);
        */



    }
}
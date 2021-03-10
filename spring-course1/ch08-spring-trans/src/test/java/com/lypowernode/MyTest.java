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

        // 调用方法
        buyService.buy(1001,10);
        buyService.buy(1002,50);

    }
}
package com.xxxx.server;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* 启动类
*
*
* */
@Slf4j
@SpringBootApplication
@MapperScan("com.xxxx.server.mapper")
public class YebApplication {

    public static void main(String[] args) {
        SpringApplication.run(YebApplication.class,args);
        log.info(" ------ SpringBoot工程 啓動成功 ------");
    }
}


package com.xxxx.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date ：Created in 2021/2/5 11:09
 * @description：测试
 * @author：
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello1(){
        return "hello";

    }

    @GetMapping("/employee/basic/hello")
    public String hello2() {
        return "/employee/basic/hello";

    }

    @GetMapping("/employee/advanced/hello")
    public String hello3() {
        return "/employee/advanced/hello";
    }
}

package com.cspowernode.service.impl;

import com.cspowernode.service.SomeService;

public class SomeServiceImpl2 implements SomeService {
    @Override
    public void doSome() {

        System.out.println("输出doSome");

    }

    @Override
    public void doOther() {

        System.out.println("输出doOther");

    }
}

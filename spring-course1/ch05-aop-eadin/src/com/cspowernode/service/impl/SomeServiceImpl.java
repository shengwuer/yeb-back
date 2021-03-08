package com.cspowernode.service.impl;

import com.cspowernode.service.SomeService;
import com.cspowernode.util.ServiceTools;

import java.util.Date;

public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome() {

        System.out.println("输出doSome");

    }

    @Override
    public void doOther() {

        System.out.println("输出doOther");

    }
}

package lypowernode.service.impl;

import lypowernode.service.SomeService;
import lypowernode.util.ServiceTools;

public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome() {

        System.out.println("执行业务方法式doSome===================================");

    }

    @Override
    public void doOther() {

        System.out.println("执行业务方法式doOther=====================");

    }

    @Override
    public void doFirst() {

    }
}

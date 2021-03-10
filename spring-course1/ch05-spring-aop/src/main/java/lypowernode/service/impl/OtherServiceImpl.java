package lypowernode.service.impl;

import lypowernode.service.OtherService;

public class OtherServiceImpl implements OtherService {
    @Override
    public void first() {
        System.out.println("执行了OtherServiceImpl类的first()");
    }

    @Override
    public void second() {
        System.out.println("执行了OtherServiceImpl类的second()");
    }
}

package com.cspowernode.ba02;

public interface SomeService {
    void doSome(String name, Integer age);
    String doOther(String name, Integer age);

    default Student doOther2(String name, Integer age) {
        return null;
    }

}

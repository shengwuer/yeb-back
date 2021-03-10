package com.lypowernode.exception;
// 自定义的运行时的异常
public class NotEnoughException extends RuntimeException {
    public NotEnoughException() {
        super();
    }

    public NotEnoughException(String s) {
        super(s);
    }
}

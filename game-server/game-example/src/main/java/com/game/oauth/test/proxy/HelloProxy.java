package com.game.oauth.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 9:31 2019/5/28 0028
 * @explain :
 */
public class HelloProxy implements InvocationHandler {

    private Object subject;

    public HelloProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("------------begin---------------");
        method.invoke(subject,args);
        System.out.println("------------end---------------");
        return null;
    }
}

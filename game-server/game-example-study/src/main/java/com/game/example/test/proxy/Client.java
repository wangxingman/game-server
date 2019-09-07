package com.game.example.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 9:34 2019/5/28 0028
 * @explain :
 */
public class Client {

    public static void main(String[] args) {
        ExampleProxyService exampleProxyService = new ExampleProxyService();
        InvocationHandler handler = new HelloProxy(exampleProxyService);
        ExampleProxy o = (ExampleProxy)Proxy.newProxyInstance(handler.getClass().getClassLoader()
                , exampleProxyService.getClass().getInterfaces(), handler);
        o.sayHello();
    }
}

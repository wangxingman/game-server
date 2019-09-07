package com.game.example.test.proxy;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 9:30 2019/5/28 0028
 * @explain :
 */
public class ExampleProxyService implements ExampleProxy {
    @Override
    public void sayHello() {
        System.out.println("输出对应的东西！");
    }
}

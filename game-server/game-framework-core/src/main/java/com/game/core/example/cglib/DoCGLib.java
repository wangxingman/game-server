package com.game.core.example.cglib;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 7:10 2019/5/20 0020
 * @explain : 创建代理 主类
 */
public final class DoCGLib {

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        //通过生成子类的方式创建代理类
        SayHello proxyImp = (SayHello)proxy.getProxy(SayHello.class);
        proxyImp.say();
    }
}

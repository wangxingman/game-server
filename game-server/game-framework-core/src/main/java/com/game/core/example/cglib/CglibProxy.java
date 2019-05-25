package com.game.core.example.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 7:10 2019/5/20 0020
 * @explain :
 */
public class CglibProxy implements MethodInterceptor {

    /**增强器*/
    private final Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        //设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例 创建代理对象
        return enhancer.create();
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:27 2019/5/20 0020 
     * @params: 
     * @Desc  :     obj 目标类的实例
     *              method 目标方法实例（通过反射获取的目标方法实例）
     *              args 目标方法的参数
     *              proxy 代理类的实例
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("前置代理");
        //通过代理类调用父类中的方法
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("后置代理");
        return result;
    }
}

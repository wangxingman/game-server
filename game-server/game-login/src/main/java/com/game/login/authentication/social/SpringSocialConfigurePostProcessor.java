package com.game.login.authentication.social;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author: wx
 * @Date  : 下午 8:49 2019/7/3 0003
 * @params:
 * @Desc  : bean初始化之前和初始化之后都会经过这个
 */
@Component
public class SpringSocialConfigurePostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(StringUtils.equals(beanName, "mySocialSecurityConfig")){
            System.out.println("找到mySocialSecurityConfig");
            MySpringSocialConfigurer configurer = (MySpringSocialConfigurer)bean;
            //app注册页
            configurer.signupUrl("/social/signUp");
            return configurer;
        }

        return bean;
    }
}

package com.game.login.config;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Auther : wx
 * @Desc :   自定义 登录校验
 * @Date :  下午 8:34 2019/5/9 0009
 * @explain : 调用登录接口时会进入到此类的attemptAuthentication方法 进行相关校验操作
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
}

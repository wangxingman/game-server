package com.game.login.config.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther : wx
 * @Desc :  认证失败 需要做的业务操作
 * @Date :  下午 8:29 2019/5/9 0009
 * @explain : 当检测到用户访问系统资源认证失败时则会进入到此类并执行相关业务
 */
public class MyAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        
    }
}

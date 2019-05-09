package com.game.login.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther :  wx
 * @Desc :    用户退出系统成功后 需要做的业务操作
 * @Date :    下午 8:33 2019/5/9 0009
 * @explain : 当用户退出系统成功后则会进入到此类并执行相关业务
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
    }
}

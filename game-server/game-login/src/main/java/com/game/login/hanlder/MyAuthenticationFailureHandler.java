package com.game.login.hanlder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.common.constant.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: wx
 * @Date  : 下午 8:28 2019/5/27 0027 
 * @params: 
 * @Desc  :  自定义失败
 */
//@Component
//public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
//
//    @Autowired
//    private ObjectMapper mapper;
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//
//        System.out.println("----登陆失败----");
//        //状态码定义500
//        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(mapper.writeValueAsString(exception.getMessage()));
//        response.sendRedirect(Const.login.LOGIN_PAGE);
//    }
//}

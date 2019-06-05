package com.game.login.hanlder;

import com.game.login.feign.FHallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: wx
 * @Date  : 下午 3:47 2019/5/28 0028
 * @params:
 * @Desc  :  自定义成功登陆成功逻辑
 *          这里使用feign 或者是webSocket连接
 */
@Component
@Slf4j
public class MyAuthenticationSucessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private FHallService hallService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
        /**
         * 用户登录成功 和网关服建立连接
         * 网关服的地址 是固定的 但是分发的信息 肯定是需要的
         */
        log.info("---登陆成功-----");
        hallService.loginGateWay(null,"1231");
        log.info("---转发到gateWay服务-----");

        /*  redirectStrategy.sendRedirect(request, response, "/success");*/
    }
}

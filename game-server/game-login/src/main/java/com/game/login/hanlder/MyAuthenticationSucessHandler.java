package com.game.login.hanlder;

import com.alibaba.fastjson.JSONObject;
import com.game.common.Const.Const;
import com.game.core.ws.clientConfig.WsSyncClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义成功登陆成功逻辑
 */

@Component
@Slf4j
public class MyAuthenticationSucessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
        /**
         * 用户登录成功 和网关服建立连接
         * 网关服的地址 是固定的 但是分发的信息 肯定是需要的
         */
        log.info("---登陆成功-----");
        String s = WsSyncClient.sendAndClose("ws://" + Const.Addr.GATE_WAY  + "/websocket", authentication.getPrincipal());
        log.info("join:" + s);
        JSONObject res = JSONObject.parseObject(s);
        JSONObject resData = JSONObject.parseObject(res.getString("data"));
        /*  redirectStrategy.sendRedirect(request, response, "/success");*/
    }
}

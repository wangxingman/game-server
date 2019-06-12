package com.game.login.hanlder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.login.feign.FHallService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
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
 *
 *          SavedRequestAwareAuthenticationSuccessHandler 实现   AuthenticationSuccessHandler
 */
@Component
@Slf4j
public class MyAuthenticationSucessHandler  extends SavedRequestAwareAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FHallService hallService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
        log.info("登录成功之后的处理");

        String type = request.getHeader("Accept");
        if(!type.contains("text/html")){

            String clientId = "app";
            String clientSecret = "app";

            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
            if (null == clientDetails) {
                throw new UnapprovedClientAuthenticationException("clientId不存在" + clientId);
            } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
                throw new UnapprovedClientAuthenticationException("clientSecret不匹配" + clientId);
            }

            TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");

            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

            OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

            System.out.println("这是生成的token"+token);

            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(token));

            /**
             * 用户登录成功 和网关服建立连接
             * 网关服的地址 是固定的 但是分发的信息 肯定是需要的
             */
            log.info("---登陆成功-----");
            hallService.loginGateWay(null,"1231");
            log.info("---转发到gateWay服务-----");

            redirectStrategy.sendRedirect(request, response, "/success");
        }else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}

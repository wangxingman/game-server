package com.game.login.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.common.dto.Login;
import com.game.common.entity.user.User;
import com.game.common.redis.RedisUtil;
import com.game.core.exception.BadRequestException;
import com.game.core.utils.LoginUtil;
import com.game.core.utils.web.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Author: wx
 * @Date : 下午 8:50 2019/7/3 0003
 * @params:
 * @Desc :
 */
@Component
@Slf4j
public class MyAuthenticationSuccessHandler<jwtTokenServices> extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    public static final String ENCRYPT = "a3caed36f0fe5a01e5f144db8927235e";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("登录成功之后的处理");

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
        RedisUtil.save("token",token.getValue(),5L);

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        RedisUtil.save("token",token.getValue(),5L);
        //storeAccessToken redis保存token
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", converter.convertAccessToken(token, oAuth2Authentication).get(ENCRYPT));
        map.put("token", token);

        LoginUtil.add(request);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }
}

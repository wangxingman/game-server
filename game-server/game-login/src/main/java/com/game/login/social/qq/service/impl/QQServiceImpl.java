package com.game.login.social.qq.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.common.entity.qq.QQUserInfo;
import com.game.login.social.qq.service.QQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:58 2019/6/10 0010
 * @explain : 获取用户的信息
 */
@Slf4j
public class QQServiceImpl extends AbstractOAuth2ApiBinding implements QQService {

    /**
     * 获取openId,需要参数token
     */
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     * 获取用户信息，需要openId和oauth_consumer_key
     */
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;
    private String openId;
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @Author: wx
     * @Date  : 下午 2:10 2019/6/10 0010 
     * @params: 
     * @Desc  :   构造方法 获取参数
     *              获取 openId
     */
    public  QQServiceImpl(String accessToken, String appId) {
        //把accessToken放进url中
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, accessToken);
        //发起请求
        String result = getRestTemplate().getForObject(url, String.class);
        log.info(result);
        //放进openId
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }
    
    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        //发起请求
        String result = getRestTemplate().getForObject(url, String.class);
        log.info(result);
        QQUserInfo userInfo;
        try {
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取用户信息失败", e);
        }
    }
    
}

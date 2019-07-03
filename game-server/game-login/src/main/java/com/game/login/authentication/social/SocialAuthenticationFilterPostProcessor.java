package com.game.login.authentication.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @Author: wx
 * @Date  : 下午 8:49 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}

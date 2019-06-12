package com.game.login.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @Author: wx
 * @Date  : 下午 3:28 2019/6/10 0010 
 * @params: 
 * @Desc  :
 */
public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}

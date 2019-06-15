package com.game.login.authentication.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @author lvhaibao
 * @description
 * @date 2019/1/8 0008 15:15
 */
public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}

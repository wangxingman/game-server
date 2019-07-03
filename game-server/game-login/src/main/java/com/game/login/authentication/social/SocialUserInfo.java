package com.game.login.authentication.social;

import lombok.Data;

/**
 * @Author: wx
 * @Date  : 下午 8:49 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
@Data
public class SocialUserInfo {

    private String providerId;

    private String providerUserId;

    private String nickname;

    private String headimg;

}

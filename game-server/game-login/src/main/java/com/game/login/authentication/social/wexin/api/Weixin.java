package com.game.login.authentication.social.wexin.api;

import com.game.login.authentication.social.wexin.model.WeixinUserInfo;

/**
 * @Author: wx
 * @Date  : 下午 8:47 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
public interface Weixin {

    WeixinUserInfo getUserInfo(String openId);
}

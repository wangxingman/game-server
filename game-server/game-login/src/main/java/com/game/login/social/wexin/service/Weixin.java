package com.game.login.social.wexin.service;

import com.game.common.entity.weixin.WeixinUserInfo;

/**
 * @Author: wx
 * @Date  : 上午 10:38 2019/6/11 0011 
 * @params: 
 * @Desc  :
 */
public interface Weixin {

    WeixinUserInfo getUserInfo(String openId);
}

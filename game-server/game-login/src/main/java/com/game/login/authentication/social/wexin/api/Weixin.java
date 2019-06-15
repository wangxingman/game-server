package com.game.login.authentication.social.wexin.api;

import com.game.login.authentication.social.wexin.model.WeixinUserInfo;

/**
 * @author lvhaibao
 * @description
 * @date 2019/1/4 0004 9:49
 */
public interface Weixin {

    WeixinUserInfo getUserInfo(String openId);
}

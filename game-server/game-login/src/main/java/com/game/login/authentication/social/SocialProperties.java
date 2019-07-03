package com.game.login.authentication.social;

import com.game.login.authentication.social.qq.QQProperties;
import com.game.login.authentication.social.wexin.WeixinProperties;
import lombok.Data;

/**
 * @Author: wx
 * @Date  : 下午 8:49 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
@Data
public class SocialProperties {

    private QQProperties qq = new QQProperties();

    private String filterProcessesUrl = "/auth";

    private WeixinProperties weixin = new WeixinProperties();
}

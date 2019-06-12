package com.game.login.social;

import com.game.login.social.qq.config.QQProperties;
import com.game.login.social.wexin.config.WeixinProperties;
import lombok.Data;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:18 2019/6/10 0010
 * @explain : 属性
 */
@Data
public class SocialProperties {

    private QQProperties qq = new QQProperties();

    private String filterProcessesUrl = "/auth";

    private WeixinProperties weixin = new WeixinProperties();
}


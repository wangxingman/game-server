package com.game.login.authentication.social;

import com.game.login.authentication.social.qq.QQProperties;
import com.game.login.authentication.social.wexin.WeixinProperties;
import lombok.Data;

/**
 * @author lvhaibao
 * @description
 * @date 2019/1/3 0003 10:57
 */
@Data
public class SocialProperties {

    private QQProperties qq = new QQProperties();

    private String filterProcessesUrl = "/auth";

    private WeixinProperties weixin = new WeixinProperties();
}

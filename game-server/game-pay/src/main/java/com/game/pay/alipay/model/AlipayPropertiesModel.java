package com.game.pay.alipay.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:12 2019/8/10 0010
 * @explain :
 */
@Data
@Component
@ConfigurationProperties(prefix = "duty")
public class AlipayPropertiesModel {
    private String appId;
    private String privateKey;
    private String publicKey;
    private String notifyUrl;
    private String returnUrl;
    private String signType;
    private String charset;
    private String gatewayUrl;
    private String logPath;
}

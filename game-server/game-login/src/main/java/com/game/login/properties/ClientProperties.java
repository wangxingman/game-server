package com.game.login.properties;

import lombok.Data;

/**
 * @Author: wx
 * @Date  : 下午 8:50 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
@Data
public class ClientProperties {
    /** 授权客户端ID */
    private String clientId;
    /** 授权客户端密钥 */
    private String clientSecret;
}

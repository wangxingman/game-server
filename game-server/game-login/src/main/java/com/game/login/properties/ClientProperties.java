package com.game.login.properties;

import lombok.Data;

@Data
public class ClientProperties {
    /** 授权客户端ID */
    private String clientId;
    /** 授权客户端密钥 */
    private String clientSecret;
}

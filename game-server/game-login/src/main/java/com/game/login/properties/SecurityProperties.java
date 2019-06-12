package com.game.login.properties;


import com.game.login.mobile.ValidateCodeProperties;
import com.game.login.social.SocialProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: wx
 * @Date  : 下午 3:20 2019/6/10 0010 
 * @params: 
 * @Desc  :
 */
@ConfigurationProperties(prefix = "system")
@Data
public class SecurityProperties {

    private OauthPageProperties oauthLogin = new OauthPageProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private SocialProperties social = new SocialProperties();

}

package com.game.login.properties;


import com.game.login.authentication.mobile.ValidateCodeProperties;
import com.game.login.authentication.social.SocialProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: wx
 * @Date  : 下午 8:50 2019/7/3 0003 
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

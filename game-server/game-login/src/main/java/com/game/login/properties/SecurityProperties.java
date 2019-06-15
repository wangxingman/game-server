package com.game.login.properties;


import com.game.login.authentication.mobile.ValidateCodeProperties;
import com.game.login.authentication.social.SocialProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lvhaibao
 * @description
 * @date 2018/11/22 0022 11:54
 */
@ConfigurationProperties(prefix = "system")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityProperties {

    private OauthPageProperties oauthLogin = new OauthPageProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private SocialProperties social = new SocialProperties();

}

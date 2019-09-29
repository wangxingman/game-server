package com.game.login.config;

import com.game.common.encode.MD5Util;
import com.game.login.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.game.login.authentication.mobile.SmsCodeFilter;
import com.game.login.authentication.openid.OpenIdAuthenticationConfig;
import com.game.login.constants.FromLoginConstant;
import com.game.login.properties.SecurityProperties;
import com.game.login.redis.VcodeManager;
import com.game.login.service.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @Author: wx
 * @Date  : 下午 5:25 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private VcodeManager vcodeManager;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private OpenIdAuthenticationConfig openIdAuthenticationConfig;
    @Autowired
    private SpringSocialConfigurer mySocialSecurityConfig;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @Author: wx
     * @Date  : 下午 3:37 2019/6/28 0028 
     * @params: 
     * @Desc  : 密码验证 ->在验证完之前
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(myUserDetailsService).passwordEncoder(new PasswordEncoder() {
            //编码
            @Override
            public String encode(CharSequence rawPassword) {
                return null;
            }
            // 匹配
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String encode = MD5Util.encode((String) rawPassword);
                return encodedPassword.equals(encode);
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**初始化 赋值 拦截的url*/
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter(vcodeManager);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // 不创建会话
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //表单登录,loginPage为登录请求的url,loginProcessingUrl为表单登录处理的URL
                .formLogin().loginPage(FromLoginConstant.LOGIN_PAGE).loginProcessingUrl(FromLoginConstant.LOGIN_PROCESSING_URL)
                //登录成功之后的处理
                .successHandler(myAuthenticationSuccessHandler)
                //允许访问
                .and().authorizeRequests().antMatchers(
                FromLoginConstant.LOGIN_PROCESSING_URL,
                FromLoginConstant.LOGIN_PAGE,
                securityProperties.getOauthLogin().getOauthLogin(),
                securityProperties.getOauthLogin().getOauthGrant(),
                "/myLogout",
                "/code/sms")
                .permitAll().anyRequest().authenticated()
                //禁用跨站伪造
                .and().csrf().disable()
                //短信验证码配置
                .apply(smsCodeAuthenticationSecurityConfig)
                //社交登录
                .and().apply(mySocialSecurityConfig)
                //openID登录
                .and().apply(openIdAuthenticationConfig);
    }

}

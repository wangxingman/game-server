package com.game.login.config;

import com.game.common.constant.Const;
import com.game.login.hanlder.MyAuthenticationFailureHandler;
import com.game.login.hanlder.MyAuthenticationSucessHandler;
import com.game.login.mobile.SmsCodeAuthenticationSecurityConfig;
import com.game.login.mobile.SmsCodeFilter;
import com.game.login.properties.SecurityProperties;
import com.game.login.redis.VcodeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 4:47 2019/5/17 0017
 * @explain : 浏览器配置
 */
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**基础属性*/
    @Autowired
    private SecurityProperties securityProperties;

    /**验证码*/
    @Autowired
    private VcodeManager vcodeManager;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Autowired
    private SpringSocialConfigurer mySocialSecurityConfig;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private MyAuthenticationSucessHandler myAuthenticationSucessHandler;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //添加拦截器
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter(vcodeManager);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                //表单登录,loginPage为登录请求的url,loginProcessingUrl为表单登录处理的URL
                .formLogin().loginPage(Const.login.LOGIN_PAGE).loginProcessingUrl(Const.login.LOGIN_PROCESSING_URL)
                .successHandler(myAuthenticationSucessHandler)
                .failureHandler(authenticationFailureHandler)
                //允许访问
                .and().authorizeRequests().antMatchers(
                Const.login.LOGIN_PROCESSING_URL,
                Const.login.LOGIN_PAGE,
                securityProperties.getOauthLogin().getOauthLogin(),
                securityProperties.getOauthLogin().getOauthGrant(),
                "/myLogout",
                "/code/sms")
//                "/oauth/**")
                .permitAll().anyRequest().authenticated()
                //禁用跨站伪造
                .and().csrf().disable()
                //短信验证码配置
                .apply(smsCodeAuthenticationSecurityConfig)
                //qq登录
                .and().apply(mySocialSecurityConfig)      ;
    }
}

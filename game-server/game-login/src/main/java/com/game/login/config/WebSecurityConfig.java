package com.game.login.config;

import com.game.common.encode.MD5Util;
import com.game.login.config.handler.MyAuthenticationFailureHandler;
import com.game.login.config.handler.MyAuthenticationSucessHandler;
import com.game.login.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 8:00 2019/5/8 0008
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSucessHandler authenticationSucessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    public static  JdbcTokenRepositoryImpl  tokenRepository;

    /**
     * 保存token
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
         tokenRepository = new JdbcTokenRepositoryImpl();
        //查询表是否存在
//        	<select id="doCheckTableIsExists" parameterType="java.util.Map"
//        resultType="String">
//                SELECT table_name FROM information_schema.TABLES WHERE
//        table_name = #{tableName} and table_schema='${dataSourceLogSchema}';
//	</select>
        
        String CREATE_TABLE_SQL = "create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, "
                + "token varchar(64) not null, last_used timestamp not null)";
//        tokenRepository.createNewToken(new PersistentRememberMeToken(null,null,null,null));
        // 如果token表不存在，使用下面语句可以初始化该表；若存在，会报错。
        return tokenRepository;
    }

    /**
     * 用户验证用户是否存在
     * @return
     */
    @Bean
    UserSecurity  getUserSecurity() {
        return new UserSecurity();
    }

    /**
     * @Author: wx
     * @Desc  : 验证
     * @Date  : 下午 8:01 2019/5/8 0008
     * @params:
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getUserSecurity()).passwordEncoder(new PasswordEncoder() {
            //编码
            @Override
            public String encode(CharSequence rawPassword) {
                String encode = MD5Util.encode((String) rawPassword);
                return encode;
            }
            // 匹配
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String encode = MD5Util.encode((String) rawPassword);
                return encodedPassword.equals(encode);
            }
        });
    }
    
    /**
     * @Author: wx
     * @Desc  : 登陆
     * @Date  : 下午 8:02 2019/5/8 0008
     * @params:
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //在security之前的拦截器，自己定义
        http.addFilterBefore(null, UsernamePasswordAuthenticationFilter.class).authorizeRequests()
                .and().formLogin()
                .loginPage("/login").loginProcessingUrl("/login")
                .successHandler(authenticationSucessHandler)
                .failureHandler(authenticationFailureHandler)
                .and().rememberMe()
                /*   .rememberMeServices(new PersistentTokenBasedRememberMeServices("syz",getUserSecurity(),persistentTokenRepository()))*/
                .tokenRepository(persistentTokenRepository())
                //设置有效时间
                .tokenValiditySeconds(60)
                .userDetailsService(getUserSecurity())
                .and().authorizeRequests().antMatchers("login.html","/login","/register","/getDemo").permitAll()
                .antMatchers().hasAnyRole("某个角色") //个人首页只允许拥有MENBER,SUPER_ADMIN角色的用户访问
                .anyRequest().authenticated()
                .and().logout().permitAll().logoutSuccessUrl("/login.html")
                .and().csrf().disable();
    }
}
    

    
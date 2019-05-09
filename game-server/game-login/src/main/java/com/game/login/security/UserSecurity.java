package com.game.login.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 5:37 2019/5/6 0006
 */
@Component
public class UserSecurity implements UserDetailsService {
    
    /**
     * @Author: wx
     * @Desc  :用户的验证
     * @Date  : 下午 5:40 2019/5/6 0006 
     * @params: 
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
         
        return null;
    }
}

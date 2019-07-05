package com.game.login.service;

import com.game.login.dao.UserDao;
import com.game.login.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: wx
 * @Date  : 下午 8:51 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
@Service
@Slf4j
public class MyUserDetailsServiceImpl implements UserDetailsService,SocialUserDetailsService {

    @Resource
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String s) {
        log.info("用户登录传参==" + s);

        UserModel userModel = this.userDao.findByUsername(s);
        if (userModel == null) {
            userModel = this.userDao.findByMobile(s);
        }
        if (userModel == null) {
            log.error("帐号未找到=" + s);
            throw new UsernameNotFoundException("帐号未找到=" + s);
        }

        return new UserModel(userModel.getUin(), userModel.getUsername(), userModel.getPassword(), userModel.getMobile());

    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("登录用户名----->"+userId);

        //数据库取到的密码，后面返回的是用户用户哪些权限
        //return new User(username, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        String password = passwordEncoder.encode("123456");
        log.info("数据库得到的密码为=="+password);
        return new SocialUser(userId ,password,true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));

    }

}

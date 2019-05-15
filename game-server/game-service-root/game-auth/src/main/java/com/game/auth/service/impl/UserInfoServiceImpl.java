package com.game.auth.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.game.auth.feign.FUserInfoService;
import com.game.auth.mapper.UserInfoMapper;
import com.game.auth.model.User;
import com.game.auth.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 2:29 2019/5/13 0013
 * @explain :
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private FUserInfoService fUserInfoService;
    
    @Override
    @Transactional
    @LcnTransaction //分布式事务注解
    public String addUserInfo() {
        User user = userInfoMapper.getOne(7);
        user.setMoney(user.getMoney()+100);
        User save = userInfoMapper.saveAndFlush(user);
        fUserInfoService.removeUserInfo();
        throw new NullPointerException();
    }
    
}

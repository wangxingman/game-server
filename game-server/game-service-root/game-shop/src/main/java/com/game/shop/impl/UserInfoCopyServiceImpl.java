package com.game.shop.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.game.shop.impl.service.UserInfoCopyService;
import com.game.shop.mapper.UserInfoMapper;
import com.game.shop.model.UserInfo;
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
public class UserInfoCopyServiceImpl implements UserInfoCopyService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    @Transactional
    @LcnTransaction //分布式事务注解
    public String removeUserInfo() {
        UserInfo userInfo = userInfoMapper.getOne(1);
        userInfo.setMoney(userInfo.getMoney()+100);
        userInfoMapper.saveAndFlush(userInfo);
        return "false";
    }
    
}

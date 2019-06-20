package com.game.auth.service.impl;

import com.game.auth.MapperConfig.AuthMapperConfig;
import com.game.auth.service.UserService;
import com.game.common.entity.user.User;
import com.game.auth.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:30 2019/6/20 0020
 * @explain :
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    @Override
    public User fastLogin(String uAccount, String password) {
        User user
                = userMapper.findByUAccountAndUPass(uAccount, password);
        if(Objects.isNull(user)) {
           log.info("不存在该用户");
        }
        return user;
    }
}

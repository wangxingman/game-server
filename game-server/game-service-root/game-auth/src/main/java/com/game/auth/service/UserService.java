package com.game.auth.service;

import com.game.common.entity.user.User;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:30 2019/6/20 0020
 * @explain :
 */
public interface UserService {
    
    User fastLogin(String uAccount, String password);
}

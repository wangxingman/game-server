package com.game.login.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.game.common.entity.user.User;
import com.game.common.mapper.UserMapper;
import com.game.login.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wx123
 * @since 2019-05-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

package com.game.hall.config.name;

import com.game.common.mapper.UserMapper;

import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:06 2019/5/30 0030
 * @explain :
 */
public interface InitName<T> {

    /**
     * @Author: wx
     * @Date  : 上午 11:45 2019/5/30 0030
     * @params:
     * @Desc  : 服务
     */
     Set<T> initName(UserMapper userMapper);

    /**
     * @Author: wx
     * @Date  : 上午 11:19 2019/5/30 0030
     * @params:
     * @Desc  :  首先数据库
     *           是全部存储在 这个集合里面
     *
     *           然后就是也做一个 退出游戏 结算的时候 将数据存入
     */
    boolean getName(String name);
}

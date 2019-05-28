package com.game.gateway.service;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 11:50 2019/5/17 0017
 * @explain :
 */
public interface HallService {

    /**
     * @Author: wx
     * @Date  : 上午 11:55 2019/5/17 0017 
     * @params: 
     * @Desc  :
     */
    default String entherHall(){
        return null;
    }

    /**
     * @Author: wx
     * @Date  : 下午 8:24 2019/5/27 0027
     * @params:  user用户 token登录成功标识
     * @Desc  :
     */
    String loginGateWay(Object user,String token);

}

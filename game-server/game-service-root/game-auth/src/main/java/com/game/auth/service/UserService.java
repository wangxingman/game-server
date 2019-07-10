package com.game.auth.service;

import com.game.common.entity.user.User;
import com.game.core.utils.jpa.UserQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:30 2019/6/20 0020
 * @explain :
 */
@CacheConfig(cacheNames = "auth")
public interface UserService {
    
    /**
     * @Author: wx
     * @Date  : 下午 2:41 2019/6/28 0028 
     * @params: 
     * @Desc  :快速登陆
     */
    User fastLogin(String username, String password);

    /**
     * @Author: wx
     * @Date  : 下午 2:42 2019/6/28 0028 
     * @params: 
     * @Desc  : 添加用户
     */
    User addUser(User user);

    /**
     * @Author: wx
     * @Date  : 下午 4:46 2019/6/28 0028 
     * @params: 
     * @Desc  :  用户修改
     */
    User updateUser(User user);
    
    /**
     * @Author: wx
     * @Date  : 上午 10:02 2019/6/29 0029 
     * @params: 
     * @Desc  :  删除一个用户
     */
    void deleteByUser(Long id);
    
    /**
     * @Author: wx
     * @Date  : 上午 10:08 2019/6/29 0029 
     * @params: 
     * @Desc  :  查询一个用户
     */
    User findByOne(Long id);

    /**
     * @Author: wx
     * @Date  : 上午 10:19 2019/6/29 0029 
     * @params: 
     * @Desc  :  查询全部的用户
     */
    List<User> findByAll();

    /**
     * @Author: wx
     * @Date  : 下午 5:53 2019/6/29 0029 
     * @params: 
     * @Desc  :  模糊查询
     */
    Object findByAllSearch(UserQueryCriteria criteria, Pageable pageable);
    
    /**
     * @Author: wx
     * @Date  : 下午 3:45 2019/7/10 0010 
     * @params: 
     * @Desc  :修改密码
     */
    void updateByPass(User user,String newPass);
}

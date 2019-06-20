package com.game.auth.mapper;

import com.game.common.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:55 2019/5/30 0030
 * @explain :
 */
@Repository
public interface UserMapper extends JpaRepository<User,Integer>,JpaSpecificationExecutor {

    /**
     * @Author: wx
     * @Date  : 上午 10:27 2019/5/31 0031 
     * @params: 
     * @Desc  :
     */
    @Query("select u from User u ")
    List<User>  selectUAccount();

    /**
     * @Author: wx
     * @Date  : 下午 2:42 2019/5/31 0031
     * @params:
     * @Desc  :
     */
    User findByUEmailOrUPhoneAndUPass(String email,String phone,String pass);

    /**
     * @Author: wx
     * @Date  : 上午 10:34 2019/6/20 0020 
     * @params: 
     * @Desc  :
     */
    User findByUAccountAndUPass(String uAccount,String uPass);
}

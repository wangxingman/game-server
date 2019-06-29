package com.game.auth.repository;

import com.game.common.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:55 2019/5/30 0030
 * @explain :
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>,JpaSpecificationExecutor {

    /**
     * @Author: wx
     * @Date  : 下午 2:48 2019/6/28 0028 
     * @params: 
     * @Desc  :
     */
    User findByUsernameAndPassword(String username,String password);

  /**
   * @Author: wx
   * @Date  : 下午 2:47 2019/6/28 0028 
   * @params: 
   * @Desc  :
   */
    User findByUsername(String username);

    /**
     * @Author: wx
     * @Date  : 下午 2:47 2019/6/28 0028 
     * @params: 
     * @Desc  :
     */
    User findByEmail(String email);


}

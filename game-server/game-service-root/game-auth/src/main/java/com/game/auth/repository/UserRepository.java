package com.game.auth.repository;

import com.game.common.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:55 2019/5/30 0030
 * @explain : JpaSpecificationExecutor实现复杂的查询
 *            在@Query注解中编写JPQL实现DELETE和UPDATE操作的时候必须加上@modifying注解，以通知Spring Data 这是一个DELETE或UPDATE操作
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

    /**
     * @Author: wx
     * @Date  : 下午 3:50 2019/7/10 0010 
     * @params: 
     * @Desc  :修改密码
     */
    @Modifying
    @Query(value = "update user set password = ?2 , last_password_reset_time = ?3 where username = ?1",nativeQuery = true)
    void updatePass(String username, String pass, Date lastPasswordResetTime);

}

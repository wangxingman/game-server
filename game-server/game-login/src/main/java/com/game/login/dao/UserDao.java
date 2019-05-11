package com.game.login.dao;


import com.game.login.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserModel, Long> {

    /**
     * 通过手机号获取用户
     *
     * @param mobile 手机号
     * @return UserModel
     */
    UserModel findByMobile(String mobile);

    /**
     * 通过账号获取用户
     *
     * @param username 账号
     * @return UserModel
     */
    UserModel findByUsername(String username);
}

package com.game.login.authentication.social;

import com.game.login.model.UserModel;
import com.game.login.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @Author: wx
 * @Date  : 下午 8:48 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

    @Autowired
    private JpaRepository jpaRepository;

    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建用户并返回用户唯一标识,当不用@Component时，就用

        //这时候，偷偷给用户添加一条user表，并且返回用户的uin

        //业务需要1
        //当用户直接用QQ登录的时候，不需要提示用户注册，后台直接注册给用户注册
        String uin = RandomUtil.randomString(6);

        UserModel userModel = new UserModel(Long.parseLong(uin), connection.getDisplayName(), "123456", null);
        //在微服务中，最好是RPC调用
        jpaRepository.save(userModel);

        //业务需求2
        //当改用户第一次注册没手机号，就提示用手机号绑定


        return connection.getDisplayName();
    }
}

package com.game.auth.mapper;


import com.game.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 2:31 2019/5/13 0013
 * @explain :
 */
public interface UserInfoMapper extends JpaRepository<User,Integer> {
}

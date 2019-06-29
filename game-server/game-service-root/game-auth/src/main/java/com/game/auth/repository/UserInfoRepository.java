package com.game.auth.repository;


import com.game.common.entity.example.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 2:31 2019/5/13 0013
 * @explain :
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
}

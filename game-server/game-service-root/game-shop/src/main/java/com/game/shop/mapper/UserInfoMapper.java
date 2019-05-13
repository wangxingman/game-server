package com.game.shop.mapper;


import com.game.shop.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 2:31 2019/5/13 0013
 * @explain :
 */
@Repository
public interface UserInfoMapper extends JpaRepository<UserInfo,Integer> {
}

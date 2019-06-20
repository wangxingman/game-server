package com.game.auth.mapper;

import com.game.common.entity.user.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:55 2019/5/30 0030
 * @explain :
 */
public interface MenuMapper extends JpaRepository<Menu,Integer>,JpaSpecificationExecutor {

}

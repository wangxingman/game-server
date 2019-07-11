package com.game.auth.repository;

import com.game.common.entity.user.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.LinkedHashSet;
import java.util.List;


/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:55 2019/5/30 0030
 * @explain :
 */
public interface MenuRepository extends JpaRepository<Menu,Long>,JpaSpecificationExecutor {

    /**
     * @Author: wx
     * @Date  : 下午 3:45 2019/7/11 0011 
     * @params: 
     * @Desc  :
     */
    LinkedHashSet<Menu> findByRoles_IdOrderBySortAsc(Long id);

    /**
     * @Author: wx
     * @Date  : 下午 3:45 2019/7/11 0011 
     * @params: 
     * @Desc  :
     */
    List<Menu> findByPid(long pid);
    
    /**
     * @Author: wx
     * @Date  : 下午 3:45 2019/7/11 0011 
     * @params: 
     * @Desc  :
     */
    Menu findByName(String name);
}

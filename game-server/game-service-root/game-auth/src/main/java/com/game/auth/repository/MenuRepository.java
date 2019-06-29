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
public interface MenuRepository extends JpaRepository<Menu,Integer>,JpaSpecificationExecutor {

    LinkedHashSet<Menu> findByRoles_IdOrderBySortAsc(Long id);

    List<Menu> findByPid(long pid);
}

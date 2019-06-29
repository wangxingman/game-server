package com.game.auth.service;

import com.game.common.entity.user.Menu;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:52 2019/6/20 0020
 * @explain :
 */
public interface MenuService {

    List<Menu> findCheckPermission();

    List<Menu> findAll();

    List<Menu> findByPid(long pid);

    Object getMenuTree(List<Menu> menus);

}

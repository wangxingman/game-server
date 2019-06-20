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
   /**
    * @Author: wx
    * @Date  : 上午 9:54 2019/6/20 0020 
    * @params: 
    * @Desc  :  有权限的所有菜单
    */
    List<Menu> findCheckPermission();
}

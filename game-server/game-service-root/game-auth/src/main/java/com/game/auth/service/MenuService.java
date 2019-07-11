package com.game.auth.service;

import com.game.common.entity.user.Menu;
import com.game.common.entity.user.Role;
import com.game.core.utils.jpa.criteria.CommonQueryCriteria;

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
     * @Date  : 下午 2:36 2019/7/11 0011 
     * @params: 
     * @Desc  :
     */
    List<Menu> findCheckPermission();

    /**
     * @Author: wx
     * @Date  : 下午 2:36 2019/7/11 0011 
     * @params: 
     * @Desc  :
     */
    List<Menu> findByPid(long pid);

    /**
     * @Author: wx
     * @Date  : 下午 2:36 2019/7/11 0011 
     * @params: 
     * @Desc  :
     */
    Object getMenuTree(List<Menu> menus);

    /**
     * @Author: wx
     * @Date  : 下午 2:36 2019/7/11 0011 
     * @params: 
     * @Desc  : 角色获取菜单
     */
    List<Menu> findByMenuToRoles(List<Role> roles);
    
    /**
     * @Author: wx
     * @Date  : 下午 2:44 2019/7/11 0011 
     * @params: 
     * @Desc  :
     */
    Object buildTree(List<Menu> menus);
    
    /**
     * @Author: wx
     * @Date  : 下午 3:27 2019/7/11 0011 
     * @params: 
     * @Desc  :
     */
    List<Menu> getByAllSearch(CommonQueryCriteria criteria);
    
    /**
     * @Author: wx
     * @Date  : 下午 3:36 2019/7/11 0011 
     * @params: 
     * @Desc  :  新增菜单
     */
    Menu addByMenu(Menu menu);
    
    /**
     * @Author: wx
     * @Date  : 下午 3:37 2019/7/11 0011 
     * @params: 
     * @Desc  : 修改菜单
     */
    Menu updateByMenu(Menu menu);
    
    /**
     * @Author: wx
     * @Date  : 下午 3:37 2019/7/11 0011 
     * @params: 
     * @Desc  :  删除菜单
     */
    void delByMenu(Long id);
    
}

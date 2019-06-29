package com.game.auth.api;

import com.game.auth.service.MenuService;
import com.game.auth.service.RoleService;
import com.game.auth.service.UserService;
import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:44 2019/6/24 0024
 * @explain :
 */
@RestController
@RequestMapping("/menu")
@Api(value = "Menu_Api")
public class MenuApi extends BaseApi {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;
    
    /**
     * @Author: wx
     * @Date  : 上午 11:49 2019/6/24 0024 
     * @params: 
     * @Desc  :  构建前端路由所需要的菜单
     */
    @GetMapping("/buildMenus")
    public Result  buildMesnus(@RequestParam String uAccount) {
        return success("菜单");
    }

    /**
     * @Author: wx
     * @Date  : 下午 5:49 2019/6/26 0026 
     * @params: 
     * @Desc  : 获取所有的菜单
     */
    @GetMapping("/menus")
    public Result menmenuTreeus() {
        Object menuTree = menuService.getMenuTree(menuService.findByPid(0L));
        return success("获取所有的菜单",menuTree);
    }
}

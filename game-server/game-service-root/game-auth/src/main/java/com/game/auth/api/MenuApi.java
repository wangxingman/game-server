package com.game.auth.api;

import com.game.auth.service.MenuService;
import com.game.auth.service.RoleService;
import com.game.auth.service.UserService;
import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.common.entity.user.Menu;
import com.game.common.entity.user.Role;
import com.game.common.entity.user.User;
import com.game.core.utils.jpa.criteria.CommonQueryCriteria;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:44 2019/6/24 0024
 * @explain :  菜单管理
 */
@RestController
@RequestMapping("/menu")
@Api(value = "Menu_Api")
public class MenuApi extends BaseApi {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * @Author: wx
     * @Date : 上午 11:49 2019/6/24 0024
     * @params:
     * @Desc :
     */
    @GetMapping("/buildMenus")
    public Result buildMesnus(@RequestParam String username) {
        //回去当前的用户
        User user = userService.findByName(username);
        //获取当前用户的 所拥有的角色
        List<Role> roleList = roleService.findByRoleToUid(user.getId());
        //获取对应菜单
        List<Menu> menuList = menuService.findByMenuToRoles(roleList);
        //转换成树的结构
        Object buildTree = menuService.buildTree(menuList);
        return success("构建当前对应角色 所对应的菜单", buildTree);
    }

    /**
     * @Author: wx
     * @Date : 下午 5:49 2019/6/26 0026
     * @params:
     * @Desc : 获取所有的菜单【直接带模糊查询】
     */
    @GetMapping("/findByAll")
    public Result findByAll() {
        Object menuTree = menuService.getMenuTree(menuService.findByPid(0L));
        return success("获取所有的菜单", menuTree);
    }

    /**
     * @Author: wx
     * @Date : 下午 2:59 2019/7/11 0011
     * @params:
     * @Desc : 模糊查询菜单
     */
    @PostMapping("/findByAllSearch")
    public Result findByAllSearch(CommonQueryCriteria criteria) {
        List<Menu> menuList = menuService.getByAllSearch(criteria);
        return success("模糊查询菜单", menuService.buildTree(menuList));
    }

    /**
     * @Author: wx
     * @Date : 上午 11:47 2019/7/11 0011
     * @params:
     * @Desc : 新增菜单
     */
    @PostMapping("/addByMenu")
    public Result addByMenu(@RequestBody Menu menu) {
        return success("新增菜单", menuService.addByMenu(menu));
    }

    /**
     * @Author: wx
     * @Date : 上午 11:47 2019/7/11 0011
     * @params:
     * @Desc : 修改菜单
     */
    @PostMapping("/updateByMenu")
    public Result updateByMenu(@RequestBody Menu menu) {
        return success();
    }

    /**
     * @Author: wx
     * @Date : 上午 11:47 2019/7/11 0011
     * @params:
     * @Desc : 删除菜单
     */
    @GetMapping("/delByMenu")
    public Result delByMenu(@RequestParam Long id) {
        return success();
    }

}

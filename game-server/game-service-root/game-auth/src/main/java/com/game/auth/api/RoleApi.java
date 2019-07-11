package com.game.auth.api;

import com.game.auth.service.RoleService;
import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.common.entity.user.Role;
import com.game.core.utils.jpa.criteria.CommonQueryCriteria;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : wx
 * @Desc :  crud   修改角色 的对应菜单  对应权限
 * @Date :  上午 10:23 2019/6/29 0029
 * @explain :
 */
@RestController
@RequestMapping("/role")
@Api(value = "Role_Api")
public class RoleApi extends BaseApi {

    @Autowired
    private RoleService roleService;

    /**
     * @Author: wx
     * @Date : 上午 10:25 2019/6/29 0029
     * @params:
     * @Desc :  获取单个role
     */
    @GetMapping("/findByOne")
    public Result findByOne(@RequestParam(name = "id") Long id) {
        return success("查询的一个角色",roleService.findById(id));
    }

    /**
     * @Author: wx
     * @Date : 上午 10:34 2019/6/29 0029
     * @params:
     * @Desc :  返回去全部的角色 【分页】
     */
    @GetMapping("findByAll")
    public Result findByAll(@PageableDefault(value = 2000, sort = {"level"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return success(roleService.findByAll(pageable));
    }

    /**
     * @Author: wx
     * @Date : 下午 4:24 2019/7/10 0010
     * @params:
     * @Desc :模糊分页查询
     */
    @GetMapping("findByAllSearch")
    public Result findByAllSearch(CommonQueryCriteria criteria, Pageable pageable) {
        return success("模糊分页查询", roleService.findByAllSearch(criteria, pageable));
    }

    /**
     * @Author: wx
     * @Date : 上午 11:14 2019/6/29 0029
     * @params:
     * @Desc : 新增角色
     */
    @PostMapping("addByRole")
    public Result addByRole(@RequestBody Role role) {
        Role roleDto = roleService.addByRole(role);
        return success("新增角色", roleDto);
    }

    /**
     * @Author: wx
     * @Date : 上午 11:24 2019/6/29 0029
     * @params:
     * @Desc : 修改角色
     */
    @PostMapping("updateByRole")
    public Result updateByRole(@RequestBody Role role) {
        Role roleDto = roleService.updatebyRole(role);
        return success("修改的角色", roleDto);
    }

    /**
     * @Author: wx
     * @Date : 上午 11:15 2019/6/29 0029
     * @params:
     * @Desc : 修改角色权限
     */
    @PostMapping("/updateByRole/permission")
    public Result updateByRolePermission(@RequestBody Role role) {
        Role currRole = roleService.updateByRolePermission(role, roleService.findById(role.getId()));
        return success("修改角色权限",currRole);
    }

    /**
     * @Author: wx
     * @Date : 上午 11:15 2019/6/29 0029
     * @params:
     * @Desc :修改角色菜单
     */
    @PostMapping("/updateByRoleMenu")
    public Result updateByRoleMenu(@RequestBody Role role) {
        Role currRole = roleService.updateByRoleMenu(role, roleService.findById(role.getId()));
        return success("修改角色菜单",currRole);
    }

    /**
     * @Author: wx
     * @Date : 上午 11:15 2019/6/29 0029
     * @params:
     * @Desc :删除角色
     */
    @GetMapping("/deleteByRole")
    public Result deleteByRole(@RequestParam(name = "id") Long id) {
        roleService.deleteByRole(id);
        return success("删除的角色");
    }

}

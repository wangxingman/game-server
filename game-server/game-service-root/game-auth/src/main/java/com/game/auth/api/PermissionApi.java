package com.game.auth.api;

import com.game.auth.service.PermissionService;
import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.common.entity.user.Permission;
import com.game.core.utils.jpa.criteria.CommonQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 12:32 2019/7/1 0001
 * @explain : 权限的操作
 */
@RestController
@RequestMapping("/permission")
public class PermissionApi extends BaseApi {

    @Autowired
    private PermissionService permissionService;

    /**
     * @Author: wx
     * @Date : 下午 7:25 2019/7/1 0001
     * @params:
     * @Desc : 查询全部的权限 新增修改时候 全部下拉菜单
     */
    @GetMapping("/findByAll")
    public Result getByAllTree() {
        return success("树形菜单", permissionService.getByAllTree(permissionService.findByPid(0L)));
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:43 2019/7/1 0001 
     * @params: 
     * @Desc  :  查询全部的权限 【模糊查询】
     */
    @GetMapping("/getByAllSearch")
    public Result getByAllSearch(CommonQueryCriteria criteria) {
        return success("全部的权限",permissionService.getByAllSearch(criteria));
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:57 2019/7/1 0001 
     * @params: 
     * @Desc  :  新增权限
     */
    @PostMapping("/addByPermission")
    public Result addByPermission(@Validated @RequestBody Permission permission) {
        return  success("添加权限数据",permissionService.addByPermission(permission)) ;
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:57 2019/7/1 0001
     * @params:
     * @Desc  :  修改权限
     */
    @PutMapping(value = "/updateByPermissions")
    public Result updateByPermissions(@Validated(Permission.Update.class) @RequestBody Permission permission){
        return success("修改权限", permissionService.updateByPermissions(permission));
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:57 2019/7/1 0001
     * @params:
     * @Desc  :  删除权限【还要删除西面的子菜单】
     */
    @DeleteMapping(value = "delByPermissions")
    public Result delByPermissions(@RequestParam Long id){
        permissionService.delByPermissions(id);
        return success("删除权限");
    }
}

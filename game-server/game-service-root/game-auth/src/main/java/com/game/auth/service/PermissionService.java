package com.game.auth.service;

import com.game.common.dto.user.PermissionDto;
import com.game.common.entity.user.Permission;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 6:13 2019/7/1 0001
 * @explain :
 */
@CacheConfig(cacheNames = "permission")
public interface PermissionService {
    /**
     * @Author: wx
     * @Date : 下午 7:27 2019/7/1 0001
     * @params:
     * @Desc :  查询全部的权限【树结构】
     */
    @Cacheable(key = "'tree'")
    Object getByAllTree(List<Permission> permissions);

    /**
     * @Author: wx
     * @Date : 下午 7:40 2019/7/1 0001
     * @params:
     * @Desc :
     */
    @Cacheable(key = "'pid:'+#p0")
    List<Permission> findByPid(long pid);

    /**
     * @Author: wx
     * @Date : 下午 7:44 2019/7/1 0001
     * @params:
     * @Desc : 查询全部的权限
     */
//    @Cacheable(keyGenerator = "keyGenerator")
    List<PermissionDto> queryAll();

    /**
     * @Author: wx
     * @Date : 下午 8:00 2019/7/1 0001
     * @params:
     * @Desc :  添加权限
     */
    PermissionDto addByPermission(Permission permission);

    /**
     * @Author: wx
     * @Date  : 下午 8:09 2019/7/1 0001 
     * @params: 
     * @Desc  :  修改权限
     */
    @CacheEvict(allEntries = true)
    PermissionDto updateByPermissions(Permission permission);

    /**
     * @Author: wx
     * @Date  : 下午 8:10 2019/7/1 0001 
     * @params: 
     * @Desc  : 删除权限【删除下面的子菜单】
     */
    @CacheEvict(allEntries = true)
    void delByPermissions(Long id);
}

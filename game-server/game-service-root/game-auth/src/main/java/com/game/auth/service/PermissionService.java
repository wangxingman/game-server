package com.game.auth.service;

import com.game.common.entity.user.Permission;
import com.game.core.utils.jpa.criteria.CommonQueryCriteria;
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
     * @Desc : 查询顶点权限菜单
     */
    @Cacheable(key = "'pid:'+#p0")
    List<Permission> findByPid(long pid);

    /**
     * @Author: wx
     * @Date : 下午 7:44 2019/7/1 0001
     * @params:
     * @Desc : 查询全部的权限
     */
    List<Permission> getByAllSearch(CommonQueryCriteria criteria);

    /**
     * @Author: wx
     * @Date : 下午 8:00 2019/7/1 0001
     * @params:
     * @Desc :  添加权限
     */
    Permission addByPermission(Permission permission);

    /**
     * @Author: wx
     * @Date  : 下午 8:09 2019/7/1 0001 
     * @params: 
     * @Desc  :  修改权限
     */
    @CacheEvict(allEntries = true)
    Permission updateByPermissions(Permission permission);

    /**
     * @Author: wx
     * @Date  : 下午 8:10 2019/7/1 0001 
     * @params: 
     * @Desc  : 删除权限【删除下面的子菜单】
     */
    @CacheEvict(allEntries = true)
    void delByPermissions(Long id);


    /**
     * @Author: wx
     * @Date  : 上午 11:06 2019/7/11 0011
     * @params:
     * @Desc  : 集合转换菜单类型数据
     */
    Object buildTree(List<Permission> permissions);
}

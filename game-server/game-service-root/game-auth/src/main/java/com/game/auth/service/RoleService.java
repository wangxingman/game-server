package com.game.auth.service;

import com.game.common.dto.user.RoleDto;
import com.game.common.entity.user.Role;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;


/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:55 2019/6/24 0024
 * @explain :
 */
@CacheConfig(cacheNames = "role")
public interface RoleService {
    
    /**
     * @Author: wx
     * @Date  : 上午 11:46 2019/6/29 0029 
     * @params: 
     * @Desc  : 查询一个角色
     */
    @Cacheable(key = "#p0")
    RoleDto findById(long id);

    /**
     * @Author: wx
     * @Date  : 上午 11:46 2019/6/29 0029 
     * @params: 
     * @Desc  :查询全部的角色 
     */
    Object findByAll(Pageable pageable);

    /**
     * @Author: wx
     * @Date  : 上午 11:46 2019/6/29 0029 
     * @params: 
     * @Desc  :  修改一个角色  【有点不知道 加入这个缓存的注解 缓存改变的时候会不会 有影响】
     */
    @CacheEvict(allEntries = true)
    RoleDto updatebyRole(Role role);

    /**
     * @Author: wx
     * @Date  : 上午 11:56 2019/6/29 0029 
     * @params: 
     * @Desc  :修改角色的菜单
     */
    @CacheEvict(allEntries = true)
    RoleDto updateByRoleMenu(Role resources, RoleDto roleDTO);

    /**
     * @Author: wx
     * @Date  : 下午 12:02 2019/6/29 0029 
     * @params: 
     * @Desc  : 删除一个角色
     */
    @CacheEvict(allEntries = true)
    void deleteByRole(Long id);

    /**
     * @Author: wx
     * @Date  : 下午 2:12 2019/6/29 0029 
     * @params: 
     * @Desc  : 添加角色
     */
    @CacheEvict(allEntries = true)
    RoleDto addByRole(Role role);
    
    /**
     * @Author: wx
     * @Date  : 下午 3:14 2019/6/29 0029 
     * @params: 
     * @Desc  :  修改角色的权限
     */
    @CacheEvict(allEntries = true)
    RoleDto updateByRolePermission(Role role, RoleDto roleDto);
    
}

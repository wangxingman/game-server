package com.game.auth.service;

import com.game.common.entity.user.Role;
import com.game.core.utils.jpa.criteria.CommonQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;

import java.util.List;


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
     * @Date : 上午 11:46 2019/6/29 0029
     * @params:
     * @Desc : 查询一个角色
     */
    Role findById(long id);

    /**
     * @Author: wx
     * @Date : 上午 11:46 2019/6/29 0029
     * @params:
     * @Desc :查询全部的角色
     */
    Object findByAll(Pageable pageable);

    /**
     * @Author: wx
     * @Date : 上午 11:46 2019/6/29 0029
     * @params:
     * @Desc :  修改一个角色  【有点不知道 加入这个缓存的注解 缓存改变的时候会不会 有影响】
     */
    @CacheEvict(allEntries = true)
    Role updatebyRole(Role role);

    /**
     * @Author: wx
     * @Date : 上午 11:56 2019/6/29 0029
     * @params:
     * @Desc :修改角色的菜单
     */
    @CacheEvict(allEntries = true)
    Role updateByRoleMenu(Role resources, Role role  );

    /**
     * @Author: wx
     * @Date : 下午 12:02 2019/6/29 0029
     * @params:
     * @Desc : 删除一个角色
     */
    @CacheEvict(allEntries = true)
    void deleteByRole(Long id);

    /**
     * @Author: wx
     * @Date : 下午 2:12 2019/6/29 0029
     * @params:
     * @Desc : 添加角色
     */
    @CacheEvict(allEntries = true)
    Role addByRole(Role role);

    /**
     * @Author: wx
     * @Date : 下午 3:14 2019/6/29 0029
     * @params:
     * @Desc :  修改角色的权限
     */
    @CacheEvict(allEntries = true)
    Role updateByRolePermission(Role role, Role currRole);

    /**
     * @Author: wx
     * @Date : 下午 4:29 2019/7/10 0010
     * @params:
     * @Desc :  模糊分页查询
     */
    Object findByAllSearch(CommonQueryCriteria criteria, Pageable pageable);

    /**
     * @Author: wx
     * @Date  : 下午 2:26 2019/7/11 0011 
     * @params: 
     * @Desc  : 用户获取所有的角色
     */
    List<Role> findByRoleToUid(Long id);

}

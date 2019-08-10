package com.game.auth.service;

import com.game.common.dto.user.DeptDto;
import com.game.common.entity.user.Dept;
import com.game.core.utils.jpa.criteria.auth.DeptQueryCriteria;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:44 2019/7/2 0002
 * @explain :
 */
public interface DeptService {

     /**
      * @Author: wx
      * @Date  : 上午 10:51 2019/7/2 0002
      * @params:
      * @Desc  :
      */
    List<Dept> queryAll(DeptQueryCriteria criteria);

    /**
     * @Author: wx
     * @Date  : 下午 7:41 2019/7/17 0017
     * @params:
     * @Desc  :
     */
    Object findByAll();
    
    /**
     * @Author: wx
     * @Date  : 上午 10:51 2019/7/2 0002
     * @params:
     * @Desc  :
     */
    @Cacheable(key = "#p0")
    Dept findById(Long id);

    /**
     * @Author: wx
     * @Date  : 上午 10:51 2019/7/2 0002
     * @params:
     * @Desc  :
     */
    @CacheEvict(allEntries = true)
    Dept addByDept(Dept dept);

    /**
     * @Author: wx
     * @Date  : 上午 10:51 2019/7/2 0002
     * @params:
     * @Desc  :
     */
    @CacheEvict(allEntries = true)
    Dept updateByDept(Dept dept);

    /**
     * @Author: wx
     * @Date  : 上午 10:51 2019/7/2 0002
     * @params:
     * @Desc  :
     */
    @CacheEvict(allEntries = true)
    void delByDept(Long id);

    /**
     * @Author: wx
     * @Date  : 上午 10:51 2019/7/2 0002
     * @params:
     * @Desc  :
     */
    Object findByAllTree(List<DeptDto> deptDtos);

    /**
     * @Author: wx
     * @Date  : 上午 10:51 2019/7/2 0002
     * @params:
     * @Desc  :
     */
    List<Dept> findByPid(long pid);

    /**
     * @Author: wx
     * @Date  : 上午 10:51 2019/7/2 0002 
     * @params: 
     * @Desc  :
     */
    Set<Dept> findByRoleIds(Long id);

}

package com.game.auth.service;

import com.game.common.dto.user.DeptDto;
import com.game.common.encode.MD5Util;
import com.game.common.entity.user.Dept;
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
//    @Cacheable(keyGenerator = "keyGenerator")
    List<DeptDto> findByAll();
    
    /**
     * @Author: wx
     * @Date  : 上午 10:51 2019/7/2 0002
     * @params:
     * @Desc  :
     */
    @Cacheable(key = "#p0")
    DeptDto findById(Long id);

    /**
     * @Author: wx
     * @Date  : 上午 10:51 2019/7/2 0002
     * @params:
     * @Desc  :
     */
    @CacheEvict(allEntries = true)
    DeptDto addByDept(Dept dept);

    /**
     * @Author: wx
     * @Date  : 上午 10:51 2019/7/2 0002
     * @params:
     * @Desc  :
     */
    @CacheEvict(allEntries = true)
    DeptDto updateByDept(Dept dept);

    /**
     * @Author: wx
     * @Date  : 上午 10:51 2019/7/2 0002
     * @params:
     * @Desc  :
     */
    @CacheEvict(allEntries = true)
    void deleteByDept(Long id);

    /**
     * @Author: wx
     * @Date  : 上午 10:51 2019/7/2 0002
     * @params:
     * @Desc  :
     */
//    @Cacheable(keyGenerator = "keyGenerator")
    Object findByAllTree(List<DeptDto> deptDtos);

    /**
     * @Author: wx
     * @Date  : 上午 10:51 2019/7/2 0002
     * @params:
     * @Desc  :
     */
//    @Cacheable(keyGenerator = "keyGenerator")
    List<Dept> findByPid(long pid);

    /**
     * @Author: wx
     * @Date  : 上午 10:51 2019/7/2 0002 
     * @params: 
     * @Desc  :
     */
    Set<Dept> findByRoleIds(Long id);

}

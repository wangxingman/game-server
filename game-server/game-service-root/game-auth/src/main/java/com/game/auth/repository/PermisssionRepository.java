package com.game.auth.repository;

import com.game.common.entity.user.Permission;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:55 2019/5/30 0030
 * @explain :
 */
@Repository
public interface PermisssionRepository extends JpaRepository<Permission,Long>,JpaSpecificationExecutor {

    /**
     * @Author: wx
     * @Date  : 下午 7:33 2019/7/1 0001 
     * @params: 
     * @Desc  :
     */
    List<Permission> findByPid(long pid);

    /**
     * @Author: wx
     * @Date  : 下午 8:03 2019/7/2 0002
     * @params: 
     * @Desc  :
     */
    Permission findByName(String name);

}

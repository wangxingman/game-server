package com.game.auth.repository;

import com.game.common.entity.user.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:46 2019/7/2 0002
 * @explain :
 */
@Repository
public interface DeptRepository extends JpaRepository<Dept,Long>, JpaSpecificationExecutor {

    /**
     * @Author: wx
     * @Date  : 上午 11:21 2019/7/2 0002 
     * @params: 
     * @Desc  :
     */
    Dept findByName(String name);

    /**
     * @Author: wx
     * @Date  : 上午 11:21 2019/7/2 0002 
     * @params: 
     * @Desc  :
     */
    List<Dept> findByPid(Long id);
    
    /**
     * @Author: wx
     * @Date  : 上午 11:21 2019/7/2 0002 
     * @params: 
     * @Desc  :
     */
    Set<Dept> findByRoles_Id(Long id);

    /**
     * @Author: wx
     * @Date  : 上午 11:56 2019/7/2 0002 
     * @params: 
     * @Desc  :
     */
    @Query(value = "select name from dept where id = ?1",nativeQuery = true)
    String findNameById(Long id);
}

package com.game.auth.repository;

import com.game.common.entity.user.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:29 2019/7/2 0002
 * @explain :
 */
@Repository
public interface DictRepository extends JpaRepository<Dict,Long>, JpaSpecificationExecutor {
    
    /**
     * @Author: wx
     * @Date  : 下午 5:48 2019/7/2 0002 
     * @params: 
     * @Desc  :
     */
    Dict findByName(String name);
}

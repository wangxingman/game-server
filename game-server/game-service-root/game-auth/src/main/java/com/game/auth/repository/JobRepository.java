package com.game.auth.repository;

import com.game.common.entity.user.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:56 2019/7/2 0002
 * @explain :
 */
@Repository
public interface JobRepository extends JpaRepository<Job,Long>, JpaSpecificationExecutor {

    /**
     * @Author: wx
     * @Date  : 上午 10:24 2019/7/2 0002 
     * @params: 
     * @Desc  :
     */
    Job findByName(String name);
}

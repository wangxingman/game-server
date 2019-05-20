package com.game.common.mapper;

import com.game.common.entity.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 11:33 2019/5/20 0020
 * @explain :
 */
public interface LogMapper extends JpaRepository<Log,Integer>, JpaSpecificationExecutor {
    
}

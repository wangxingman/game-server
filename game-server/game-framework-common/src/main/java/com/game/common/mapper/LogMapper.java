package com.game.common.mapper;

import com.game.common.entity.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:33 2019/5/20 0020
 * @explain :
 */
@Repository
public interface LogMapper extends JpaRepository<Log,Integer> {


}

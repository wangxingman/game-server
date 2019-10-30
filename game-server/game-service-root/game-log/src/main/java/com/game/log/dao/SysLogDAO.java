package com.game.log.dao;

import com.game.log.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: @
 * @Desc:
 * @Date: 下午 8:11 2019/10/29 0029
 * @Param null
 */
public interface SysLogDAO extends JpaRepository<SysLog, Long> {
}

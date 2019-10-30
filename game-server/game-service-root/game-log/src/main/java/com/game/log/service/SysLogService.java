package com.game.log.service;

import com.game.log.dao.SysLogDAO;
import com.game.log.entity.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: @
 * @Desc:
 * @Date: 下午 7:59 2019/10/29 0029
 * @Param null
 */
@Service
public class SysLogService {

    @Autowired
    SysLogDAO sysLogDAO;

    public void saveLogger(SysLog sysLog){
        sysLogDAO.save(sysLog);
    }
}

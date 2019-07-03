package com.game.auth.service;

import com.game.common.dto.user.JobDto;
import com.game.common.entity.user.Job;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:50 2019/7/2 0002
 * @explain :
 */
public interface JobService {

    /**
     * @Author: wx
     * @Date : 上午 9:44 2019/7/2 0002
     * @params:
     * @Desc :   查询 【default即是可以选择 不重写】
     */
    default Object findByAll() {
        return null;
    }
    
    /**
     * @Author: wx
     * @Date  : 上午 10:18 2019/7/2 0002 
     * @params: 
     * @Desc  :  查询一个
     */
    JobDto findById(Long id);

    /**
     * @Author: wx
     * @Date : 上午 9:45 2019/7/2 0002
     * @params:
     * @Desc :  添加
     */
    JobDto addByJob(Job job);

    /**
     * @Author: wx
     * @Date : 上午 9:45 2019/7/2 0002
     * @params:
     * @Desc : 修改
     */
    JobDto updateByJob(Job job);

    /**
     * @Author: wx
     * @Date : 上午 9:46 2019/7/2 0002
     * @params:
     * @Desc : 删除
     */
    void delByJob(Long id);
}

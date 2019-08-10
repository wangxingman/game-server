package com.game.auth.service;

import com.game.common.entity.user.Job;
import com.game.core.utils.jpa.criteria.auth.JobQueryCriteria;
import org.springframework.data.domain.Pageable;

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
    default Object findBySearchAll(JobQueryCriteria criteria, Pageable pageable) {
        return null;
    }

    /**
     * @Author: wx
     * @Date  : 下午 3:08 2019/7/17 0017
     * @params:
     * @Desc  :
     */
    Object findByAll();
    
    /**
     * @Author: wx
     * @Date  : 上午 10:18 2019/7/2 0002 
     * @params: 
     * @Desc  :  查询一个
     */
    Job findById(Long id);

    /**
     * @Author: wx
     * @Date : 上午 9:45 2019/7/2 0002
     * @params:
     * @Desc :  添加
     */
    Job addByJob(Job job);

    /**
     * @Author: wx
     * @Date : 上午 9:45 2019/7/2 0002
     * @params:
     * @Desc : 修改
     */
    Job updateByJob(Job job);

    /**
     * @Author: wx
     * @Date : 上午 9:46 2019/7/2 0002
     * @params:
     * @Desc : 删除
     */
    void delByJob(Long id);
}

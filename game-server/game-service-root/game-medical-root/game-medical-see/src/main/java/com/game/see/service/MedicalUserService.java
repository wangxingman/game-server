package com.game.see.service;

import com.game.see.entity.MedicalUser;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:39 2019/7/25 0025
 * @explain :
 */
public interface MedicalUserService {
    
    /**
     * @Author: wx
     * @Date  : 上午 10:40 2019/7/25 0025 
     * @params: 
     * @Desc  : 查询一个用户
     */
    MedicalUser getByMedicalUser(Long id);

    /**
     * @Author: wx
     * @Date  : 下午 12:01 2019/7/26 0026
     * @params:
     * @Desc  :添加一位患者
     */
    MedicalUser addByMedicalUser(MedicalUser medicalUser);
}

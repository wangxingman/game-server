package com.game.see.service;

import com.game.see.entity.MedicalOffice;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:22 2019/7/26 0026
 * @explain :
 */
public interface MedicalOfficeService {
    
    /**
     * @Author: wx
     * @Date  : 上午 10:22 2019/7/26 0026 
     * @params: 
     * @Desc  : 添加科室
     */
    MedicalOffice addByMedicalOffice(MedicalOffice medicalOffice);
    
    /**
     * @Author: wx
     * @Date  : 上午 11:29 2019/7/26 0026 
     * @params: 
     * @Desc  : id查询某个科室
     */
    MedicalOffice selectByOne(Long id);
    
}

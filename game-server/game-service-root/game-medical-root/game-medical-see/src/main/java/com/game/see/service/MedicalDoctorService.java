package com.game.see.service;

import com.game.see.entity.MedicalDoctor;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:06 2019/7/26 0026
 * @explain :
 */
public interface MedicalDoctorService {

    /**
     * @Author: wx
     * @Date  : 上午 10:08 2019/7/26 0026 
     * @params: 
     * @Desc  :
     */
    MedicalDoctor addByMedicalDoctor(MedicalDoctor medicalDoctor);
    
    /**
     * @Author: wx
     * @Date  : 上午 11:37 2019/7/26 0026 
     * @params: 
     * @Desc  :
     */
    MedicalDoctor findByOne(Long id);
}

package com.game.see.service;

import com.game.see.entity.MedicalSubscribe;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:16 2019/7/26 0026
 * @explain :
 */
public interface MedicalSubscribeService {
    
    /**
     * @Author: wx
     * @Date  : 下午 2:17 2019/7/26 0026 
     * @params: 
     * @Desc  : 用户预约
     */
    MedicalSubscribe addMedicalSubscribe(MedicalSubscribe medicalSubscribe);

    /**
     * @Author: wx
     * @Date  : 下午 2:17 2019/7/26 0026 
     * @params: 
     * @Desc  : 查询一条预约数据
     */
    MedicalSubscribe findByOne(Long id);
}

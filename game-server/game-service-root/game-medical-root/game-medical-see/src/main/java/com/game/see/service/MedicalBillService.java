package com.game.see.service;

import com.game.see.entity.MedicalBill;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:44 2019/7/26 0026
 * @explain :
 */
public interface MedicalBillService {
    
    /**
     * @Author: wx
     * @Date  : 下午 2:45 2019/7/26 0026 
     * @params: 
     * @Desc  :
     */
    MedicalBill addMedicalBill(MedicalBill medicalBill);

    /**
     * @Author: wx
     * @Date  : 下午 2:45 2019/7/26 0026 
     * @params: 
     * @Desc  :
     */
    MedicalBill findByOne(Long id);
}

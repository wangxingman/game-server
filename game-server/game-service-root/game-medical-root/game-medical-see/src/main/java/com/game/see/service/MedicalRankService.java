package com.game.see.service;


import com.game.see.entity.MedicalRank;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:39 2019/7/25 0025
 * @explain :
 */
public interface MedicalRankService {

    /**
     * @Author: wx
     * @Date  : 上午 9:41 2019/7/26 0026 
     * @params: 
     * @Desc  :
     */
    MedicalRank addByMedicalRank(MedicalRank medicalRank);

    /**
     * @Author: wx
     * @Date  : 上午 9:41 2019/7/26 0026 
     * @params: 
     * @Desc  :
     */
    MedicalRank updateByMedicalRank(MedicalRank medicalRank);
    
    /**
     * @Author: wx
     * @Date  : 上午 11:53 2019/7/26 0026 
     * @params: 
     * @Desc  :
     */
    MedicalRank findByOne(Long id);
}

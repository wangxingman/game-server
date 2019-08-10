package com.game.see.service;

import com.game.see.entity.MedicalRankDetail;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:30 2019/8/8 0008
 * @explain :
 */
public interface MedicalRankDetailService {

    /**
     * @Author: wx
     * @Date  : 下午 5:30 2019/8/8 0008
     * @params:  status 当前医生 是换班 还是不工作
     * @Desc  : 修改排班明细
     */
    MedicalRankDetail updateByMedicalRankDetail(MedicalRankDetail medicalRankDetail,Integer status);
}

package com.game.see.service;

import com.game.see.entity.MedicalRankDetail;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:30 2019/8/8 0008
 * @explain :
 */
public interface MedicalRankDetailService {

    /**
     * @Author: wx
     * @Date : 下午 5:30 2019/8/8 0008
     * @params: status 当前医生 是换班 还是不工作
     * @Desc : 修改排班明细
     */
    MedicalRankDetail updateByMedicalRankDetail(MedicalRankDetail medicalRankDetail, Integer status);

    /**
     * @Author: wx
     * @Date : 下午 4:11 2019/8/12 0012
     * @params: 排班明细信息 / 科室id
     * @Desc : 添加排班
     */
    MedicalRankDetail addByMedicalRankDetail(MedicalRankDetail medicalRankDetail, Long officeId);

    /**
     * @Author: wx
     * @Date : 下午 4:13 2019/8/12 0012
     * @params:
     * @Desc : 删除排班
     */
    void delByMedicalRankDetail(Long id);

    /**
     * @Author: wx
     * @Date : 下午 4:14 2019/8/12 0012
     * @params:
     * @Desc :  查询排班
     */
    List<MedicalRankDetail> findAllByMedicalRankDetail(MedicalRankDetail medicalRankDetail);

    /**
     * @Author: wx
     * @Date : 下午 4:41 2019/8/12 0012
     * @params: 用户id / 科室的id / 对应的排班id
     * @Desc :患者预约排班
     */
    void addSubscribeRank(String uid, Long officeId, Long id);
    
    /**
     * @Author: wx
     * @Date  : 上午 10:10 2019/8/13 0013 
     * @params: 
     * @Desc  :
     */
    void delSubscribeRank(String uid, Long officeId, Long id);
}

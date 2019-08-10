package com.game.see.api;

import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.see.entity.MedicalRankDetail;
import com.game.see.service.MedicalRankDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:26 2019/8/8 0008
 * @explain : 排班明细
 */
@RestController
@RequestMapping("/medicalRankDetail")
public class MedicalRankDetailApi extends BaseApi {

    @Autowired
    private MedicalRankDetailService medicalRankDetailService;

    /**
     * @Author: wx
     * @Date : 下午 5:27 2019/8/8 0008
     * @params:
     * @Desc : 修改排班明细
     */
    @PostMapping("/updateByMedicalRankDetail")
    public Result updateByMedicalRankDetail(@RequestBody MedicalRankDetail medicalRankDetail, @RequestParam Integer status) {
        return success("排班明细", medicalRankDetailService.updateByMedicalRankDetail(medicalRankDetail,status));
    }

}

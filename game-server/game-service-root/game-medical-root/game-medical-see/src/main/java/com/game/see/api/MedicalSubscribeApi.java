package com.game.see.api;

import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.see.entity.MedicalSubscribe;
import com.game.see.service.MedicalSubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:58 2019/7/26 0026
 * @explain : 患者预约
 */
@RestController
@RequestMapping("/medicalSubscribe")
public class MedicalSubscribeApi extends BaseApi {

    @Autowired
    private MedicalSubscribeService medicalSubscribeService;

    /**
     * @Author: wx
     * @Date : 下午 3:18 2019/8/13 0013
     * @params:
     * @Desc : 患者看诊
     */
    @GetMapping("/updateByMedicalSubscribe")
    public Result updateByMedicalSubscribe(@RequestBody MedicalSubscribe medicalSubscribe) {
        return success("患者看诊", medicalSubscribeService.updateByMedicalSubscribe(medicalSubscribe));
    }
}


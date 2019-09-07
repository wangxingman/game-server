package com.game.see.api.handleApi;

import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.see.entity.handle.MedicalRecycle;
import com.game.see.service.handle.MedicalRecycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:40 2019/8/2 0002
 * @explain : 器械回收
 */
public class MedicalRecycleApi extends BaseApi {

    @Autowired
    private MedicalRecycleService medicalRecycleService;

    /**
     * @Author: wx
     * @Date  : 下午 5:43 2019/8/13 0013 
     * @params: 
     * @Desc  :添加回收信息
     */
    @PostMapping("/addByMedicalRecycle")
    public Result addByMedicalRecycle(@RequestBody MedicalRecycle medicalRecycle) {
        medicalRecycleService.addMedicalRecycleService(medicalRecycle);
        return success("添加回收信息");
    }
}

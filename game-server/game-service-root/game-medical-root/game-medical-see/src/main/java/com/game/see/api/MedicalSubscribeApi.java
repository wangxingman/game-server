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
     * @Date : 下午 2:09 2019/7/26 0026
     * @params:
     * @Desc : 用户预约
     */
    @PostMapping("/addMedicalSubscribe")
    public Result addMedicalSubscribe(@RequestBody MedicalSubscribe medicalSubscribe) {
        return success("用户预约", medicalSubscribeService.addMedicalSubscribe(medicalSubscribe));
    }

    /**
     * @Author: wx
     * @Date : 下午 2:14 2019/7/26 0026
     * @params:
     * @Desc :  查询一条预约数据
     */
    @GetMapping("/findByOne")
    public Result findByOne(@RequestParam Long id) {
        return success("查询一条预约数据", medicalSubscribeService.findByOne(id));
    }

    /**
     * @Author: wx
     * @Date  : 下午 2:15 2019/7/26 0026 
     * @params:
     * @Desc  : 查询全部的预约数据
     */
}


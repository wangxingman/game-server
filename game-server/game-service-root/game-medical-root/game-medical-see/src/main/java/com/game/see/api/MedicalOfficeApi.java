package com.game.see.api;

import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.see.entity.MedicalOffice;
import com.game.see.service.MedicalOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:17 2019/7/26 0026
 * @explain :科室操作
 */
@RestController
@RequestMapping("/medicalOffice")
public class MedicalOfficeApi extends BaseApi {

    @Autowired
    private MedicalOfficeService medicalOfficeService;

    /**
     * @Author: wx
     * @Date : 上午 10:18 2019/7/26 0026
     * @params:
     * @Desc : 添加科室
     */
    @PostMapping("/addByMedicalOffice")
    public Result addByMedicalOffice(@RequestBody MedicalOffice medicalOffice) {
        return success("添加科室", medicalOfficeService.addByMedicalOffice(medicalOffice));
    }

    /**
     * @Author: wx
     * @Date : 上午 11:23 2019/7/26 0026
     * @params:
     * @Desc : 查询一条信息
     */
    @GetMapping("/findByOne")
    public Result selectByOne(@RequestParam Long id) {
        return success("查询一条信息", medicalOfficeService.selectByOne(id));
    }


}

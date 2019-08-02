package com.game.see.api;

import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.see.entity.MedicalUser;
import com.game.see.service.MedicalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:04 2019/7/25 0025
 * @explain :  患者
 */
@RestController
@RequestMapping("/medicalUser")
public class MedicalUserApi extends BaseApi {

    @Autowired
    private MedicalUserService medicalUserService;

    /**
     * @Author: wx
     * @Date : 上午 10:08 2019/7/25 0025
     * @params:
     * @Desc :   查询一个患者信息
     */
    @GetMapping("/getByMedicalUser")
    public Result getByMedicalUser(@RequestParam Long id) {
        return success("查询一个患者信息", medicalUserService.getByMedicalUser(id));
    }

    /**
     * @Author: wx
     * @Date  : 上午 11:59 2019/7/26 0026
     * @params:
     * @Desc  : 添加一个基本患者信息
     */
    @PostMapping("/addByMedicalUser")
    public Result addByMedicalUser(@RequestBody MedicalUser medicalUser) {
        return success("查询一个患者信息", medicalUserService.addByMedicalUser(medicalUser));
    }
    
}

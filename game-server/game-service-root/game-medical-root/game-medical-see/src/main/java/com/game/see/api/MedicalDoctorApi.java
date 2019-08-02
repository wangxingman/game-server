package com.game.see.api;

import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.see.entity.MedicalDoctor;
import com.game.see.service.MedicalDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:01 2019/7/26 0026
 * @explain : 医生
 */
@RestController
@RequestMapping("/medicalDoctor")
public class MedicalDoctorApi extends BaseApi {

    @Autowired
    private MedicalDoctorService medicalDoctorService;
    
    /**
     * @Author: wx
     * @Date  : 上午 10:03 2019/7/26 0026 
     * @params: 
     * @Desc  : 添加医生
     */
    @PostMapping("/addByMedicalDoctor")
    public Result addByMedicalDoctor(@RequestBody MedicalDoctor medicalDoctor) {
          return success("添加医生",medicalDoctorService.addByMedicalDoctor(medicalDoctor));
    }
    
    /**
     * @Author: wx
     * @Date  : 上午 11:35 2019/7/26 0026 
     * @params: 
     * @Desc  : 查询一个医生
     */
    @GetMapping("/findByOne")
    public Result findByOne(@RequestParam Long id) {
        return success("查询一个医生",medicalDoctorService.findByOne(id));
    }
    
    
}

package com.game.see.api;

import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.see.entity.MedicalBill;
import com.game.see.service.MedicalBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:38 2019/7/26 0026
 * @explain : 病单
 */
@RestController
@RequestMapping("/MedicalBill")
public class MedicalBillApi extends BaseApi {

    @Autowired
    private MedicalBillService medicalBillService;

    /**
     * @Author: wx
     * @Date : 下午 2:39 2019/7/26 0026
     * @params:
     * @Desc : 添加病单
     */
    @PostMapping("/addMedicalBill")
    public Result addMedicalBill(@RequestBody MedicalBill medicalBill) {
        return success("添加病单", medicalBillService.addMedicalBill(medicalBill));
    }

    /**
     * @Author: wx
     * @Date : 下午 2:39 2019/7/26 0026
     * @params:
     * @Desc : 查询病单
     */
    @GetMapping("/findByOne")
    public Result addMedicalBill(@RequestParam Long id) {
        return success("添加病单", medicalBillService.findByOne(id));
    }
}

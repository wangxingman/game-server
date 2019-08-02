package com.game.see.api;

import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.see.entity.MedicalRank;
import com.game.see.service.MedicalRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:33 2019/7/25 0025
 * @explain :  添加排班
 */
@RestController
@RequestMapping("/medicalRank")
public class MedicalRankApi extends BaseApi {

    @Autowired
    private MedicalRankService medicalRankService;

    /**
     * @Author: wx
     * @Date : 下午 5:03 2019/7/25 0025
     * @params:
     * @Desc : 添加排班信息
     */
    @PostMapping("/addByMedicalRank")
    public Result addByMedicalRank(@RequestBody MedicalRank medicalRank) {
        return success("添加排班信息", medicalRankService.addByMedicalRank(medicalRank));
    }

    /**
     * @Author: wx
     * @Date : 上午 11:51 2019/7/26 0026
     * @params:
     * @Desc :  流水号查询排班
     */
    @GetMapping("findByOne")
    public Result findByOne(@RequestParam Long id) {
        return success("流水号查询排班", medicalRankService.findByOne(id));
    }

    /**
     * @Author: wx
     * @Date : 下午 5:11 2019/7/25 0025
     * @params:
     * @Desc : 修改排班
     */
    @PostMapping("/updateByMedicalRank")
    public Result updateByMedicalRank(@RequestBody MedicalRank medicalRank) {
        return success("修改排班", medicalRankService.updateByMedicalRank(medicalRank));
    }

    /**
     * @Author: wx
     * @Date : 下午 5:12 2019/7/25 0025
     * @params:
     * @Desc :  模糊分页查询
     */
    @PostMapping("/findByAllSearch")
    public Result findByAllSearch() {
        return success();
    }
}

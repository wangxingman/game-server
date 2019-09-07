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
        return success("排班明细", medicalRankDetailService.updateByMedicalRankDetail(medicalRankDetail, status));
    }

    /**
     * @Author: wx
     * @Date : 下午 4:09 2019/8/12 0012
     * @params:
     * @Desc : 添加排班
     */
    @PostMapping("/addByMedicalRankDetail")
    public Result addByMedicalRankDetail(@RequestBody MedicalRankDetail medicalRankDetail,@RequestParam Long officeId) {
        return success("添加排班", medicalRankDetailService.addByMedicalRankDetail(medicalRankDetail,officeId));
    }

    /**
     * @Author: wx
     * @Date : 下午 4:09 2019/8/12 0012
     * @params:
     * @Desc : 删除排班
     */
    @PostMapping("/delByMedicalRankDetail")
    public Result delByMedicalRankDetail(@RequestParam Long id) {
        medicalRankDetailService.delByMedicalRankDetail(id);
        return success("删除排班");
    }

    /**
     * @Author: wx
     * @Date : 下午 4:41 2019/8/12 0012
     * @params:
     * @Desc :患者预约排班
     */
    @PostMapping("/addSubscribeRank")
    public Result addSubscribeRank(@RequestParam String uid, @RequestParam Long id, @RequestParam Long officeId) {
        medicalRankDetailService.addSubscribeRank(uid, officeId, id);
        return success("患者预约排班");
    }

    /**
     * @Author: wx
     * @Date : 下午 4:41 2019/8/12 0012
     * @params:
     * @Desc :患者删除排班
     */
    @PostMapping("/delSubscribeRank")
    public Result delSubscribeRank(@RequestParam String uid, @RequestParam Long id, @RequestParam Long officeId) {
        medicalRankDetailService.delSubscribeRank(uid, officeId, id);
        return success("患者预约排班");
    }

    /**
     * @Author: wx
     * @Date : 下午 4:41 2019/8/12 0012
     * @params:
     * @Desc :患者修改排班 【个人觉得 这个排班 肯定是先改掉 再添加 没有修改】
     */
    @PostMapping("/updateSubscribeRank")
    public Result updateSubscribeRank(@RequestParam String uid, @RequestParam Long id, @RequestParam Long officeId) {
        return success("患者预约排班");
    }

}

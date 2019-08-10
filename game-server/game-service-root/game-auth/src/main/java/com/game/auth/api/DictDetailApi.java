package com.game.auth.api;

import com.game.auth.service.DictDetailService;
import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.common.entity.user.DictDetail;
import com.game.core.utils.jpa.criteria.auth.DictDetailQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:18 2019/7/2 0002
 * @explain :  字典明细
 */
@RestController
@RequestMapping("/dictDetail")
public class DictDetailApi extends BaseApi {

    @Autowired
    private DictDetailService dictDetailService;

    /**
     * @Author: wx
     * @Date  : 下午 2:21 2019/7/2 0002 
     * @params: 
     * @Desc  :   查询字典详情
     */
    @GetMapping("findByAll")
    public Result findByAll(DictDetailQueryCriteria criteria, Pageable pageable) {
        return success("字典明细All",dictDetailService.findByAll(criteria,pageable));
    }
    
    /**
     * @Author: wx
     * @Date  : 下午 2:21 2019/7/2 0002 
     * @params: 
     * @Desc  :   新增字典详情
     */
    @PostMapping("/addByDictDetail")
    public Result addByDictDetail(@RequestBody DictDetail dictDetail) {
        return success("新增字典详情",dictDetailService.addByDictDetail(dictDetail));
    }
    
    /**
     * @Author: wx
     * @Date  : 下午 2:21 2019/7/2 0002 
     * @params: 
     * @Desc  : 修改字典详情
     */
    @PostMapping("/updateByDictDetail")
    public Result updateByDictDetail(@RequestBody DictDetail dictDetail) {
        return success("修改字典详情",dictDetailService.updateByDictDetail(dictDetail));
    }
    
    /**
     * @Author: wx
     * @Date  : 下午 2:21 2019/7/2 0002 
     * @params: 
     * @Desc  :  删除字典详情
     */
    @PostMapping("/deleteByDictDetail")
    public Result deleteByDictDetail(@RequestParam Long id) {
        dictDetailService.deleteByDictDetail(id);
        return success("删除字典详情");
    }

}

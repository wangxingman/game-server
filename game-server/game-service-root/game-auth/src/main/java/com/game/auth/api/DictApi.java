package com.game.auth.api;

import com.game.auth.service.DictService;
import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.common.dto.user.DictDto;
import com.game.common.entity.user.Dict;
import com.game.core.utils.jpa.criteria.DictQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:13 2019/7/2 0002
 * @explain :
 */
@RestController
@RequestMapping("/dict")
public class DictApi extends BaseApi {

    @Autowired
    private DictService dictService;

    /**
     * @Author: wx
     * @Date : 下午 2:15 2019/7/2 0002
     * @params:
     * @Desc :  查询字典
     */
    @GetMapping("/findByAll")
    public Result findByAll(DictQueryCriteria dictQueryCriteria, Pageable pageable) {
        return success("字典数据", dictService.findByAll(dictQueryCriteria,pageable));
    }

    /**
     * @Author: wx
     * @Date : 下午 2:15 2019/7/2 0002
     * @params:
     * @Desc :  新增字典
     */
    @PostMapping("/addByDict")
    public Result addByDict(@RequestBody Dict dict) {
        return success("新增字典",dictService.addByDict(dict));
    }

    /**
     * @Author: wx
     * @Date  : 下午 2:16 2019/7/2 0002 
     * @params:
     * @Desc  : 修改字典
     */
    @PostMapping("/updateByDict")
    public Result updateByDict(@RequestBody Dict dict) {
        return success("修改字典",dictService.updateByDict(dict));
    }

    /**
     * @Author: wx
     * @Date  : 下午 2:16 2019/7/2 0002 
     * @params:
     * @Desc  :   删除字典
     */
    @PostMapping("/deleteByDict")
    public Result deleteByDict(@RequestParam Long id) {
        dictService.deleteByDict(id);
        return success("删除字典");
    }

}

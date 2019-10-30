package com.game.auth.api;

import com.game.auth.service.DeptService;
import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.core.utils.jpa.criteria.auth.DeptQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:43 2019/7/2 0002
 * @explain :
 */
@RestController
@RequestMapping("dept")
public class DeptApi extends BaseApi {

    @Autowired
    private DeptService deptService;

    /**
     * @Author: wx
     * @Date : 下午 4:23 2019/7/11 0011
     * @params:
     * @Desc :  查询部门
     */
    @PostMapping("/findByAllSearch")
    public Result findByAllSearch(DeptQueryCriteria criteria) {
        return success();
    }

    /**
     * @Author: wx
     * @Date : 下午 4:23 2019/7/11 0011
     * @params:
     * @Desc :   查询部门
     */
    @GetMapping("/findByAll")
    public Result findByAll() {
        Object menuTree = deptService.getDeptTree(deptService.findByPid(0L));
        return success("查询部门", menuTree);
    }

    /**
     * @Author: wx
     * @Date  : 下午 4:23 2019/7/11 0011 
     * @params:
     * @Desc  : 新增部门
     */
    @PostMapping("/addByDept")
    public Result addByDept() {
        return success("新增部门");
    }

    /**
     * @Author: wx
     * @Date  : 下午 4:23 2019/7/11 0011 
     * @params:
     * @Desc  : 修改部门
     */
    @PostMapping("/updateByDept")
    public Result updateByDept() {
        return success("修改部门");
    }

    /**
     * @Author: wx
     * @Date  : 下午 4:23 2019/7/11 0011 
     * @params:
     * @Desc  : 删除部门
     */
    @PostMapping("/delByDept")
    public Result delByDept() {
        return success("删除部门");
    }
}

package com.game.auth.api;

import com.game.auth.service.JobService;
import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.common.entity.user.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 8:27 2019/7/1 0001
 * @explain : 岗位操作
 */
@RestController
@RequestMapping("/job")
public class JobApi extends BaseApi {

    @Autowired
    private JobService jobService;

    /**
     * @Author: wx
     * @Date : 上午 9:44 2019/7/2 0002
     * @params:
     * @Desc :   查询
     */
    @GetMapping("findByAll")
    public Result findByAll() {
        return success( "job数据",jobService.findByAll());
    }

    /**
     * @Author: wx
     * @Date : 上午 9:45 2019/7/2 0002
     * @params:
     * @Desc :  添加
     */
    @PostMapping("addByJob")
    public Result addByJob(@RequestBody Job job) {
        return success("添加岗位",jobService.addByJob(job));
    }

    /**
     * @Author: wx
     * @Date : 上午 9:45 2019/7/2 0002
     * @params:
     * @Desc : 修改
     */
    @PostMapping("updateByJob")
    public Result updateByJob(@RequestBody Job job) {
        return success("修改岗位",jobService.updateByJob(job));
    }

    /**
     * @Author: wx
     * @Date : 上午 9:46 2019/7/2 0002
     * @params:
     * @Desc : 删除
     */
    @GetMapping("delByJob")
    public Result delByJob(@RequestParam Long id) {
        jobService.delByJob(id);
        return success("删除岗位");
    }

}

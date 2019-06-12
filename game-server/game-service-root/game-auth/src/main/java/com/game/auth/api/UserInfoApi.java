package com.game.auth.api;

import com.game.auth.service.UserInfoService;
import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.core.example.cglib.Random.ExampleRandom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 2:26 2019/5/13 0013
 * @explain :
 */
@RestController
@Api(tags="auth_API")
public class UserInfoApi extends BaseApi {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * @Author: wx
     * @Date  : 下午 4:47 2019/6/6 0006 
     * @params: 
     * @Desc  :  随机用户名
     */
    @GetMapping("/randomName")
    public Result randomName() {
        String random = ExampleRandom.getStringRandom((int) (1 + Math.random() * (10 - 1 + 1)));
        return success(random);
    }
    
    /**
     * @Author: wx
     * @Date  : 下午 12:16 2019/5/15 0015 
     * @params: 测试feign lcb
     * @Desc  :
     */
    @GetMapping("/addUserInfo")
    public Result addUserInfo() {
        String userInfo = userInfoService.addUserInfo();
        return success(userInfo);
    }

    /**
     * @Author: wx
     * @Date  : 下午 12:17 2019/5/15 0015 
     * @params: 
     * @Desc  : 测试swagger的配置
     */
    @GetMapping("/exampleSwagger")
    @ApiOperation("用户的swagger的测试")
    @ApiImplicitParam(name="id",value="查询ID",required=true)
    public String exampleSwagger() {
          return "--------------------------";
    }

}

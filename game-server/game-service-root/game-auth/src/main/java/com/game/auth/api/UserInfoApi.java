package com.game.auth.api;

import com.game.auth.service.UserInfoService;
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
public class UserInfoApi {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * @Author: wx
     * @Date  : 下午 12:16 2019/5/15 0015 
     * @params: 测试feign lcb
     * @Desc  :
     */
    @GetMapping("/addUserInfo")
    public String addUserInfo() {
        String userInfo = userInfoService.addUserInfo();
        return  "12313";
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

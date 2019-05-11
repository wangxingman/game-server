package com.game.login.controller;

import com.game.common.Const.SWCodeEnum;
import com.game.common.comman.ResultModel;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LogoutController {

    @Resource
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/myLogout")
    public ResultModel wuJinLogout(@RequestParam("token")String accessToken){
        if (consumerTokenServices.revokeToken(accessToken)) {
            return ResultModel.ok();
        }
        return ResultModel.fail(SWCodeEnum.CODE_20004);
    }


}

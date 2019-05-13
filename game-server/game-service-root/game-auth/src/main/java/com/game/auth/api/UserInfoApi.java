package com.game.auth.api;

import com.game.auth.service.UserInfoService;
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
public class UserInfoApi {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/addUserInfo")
    public String addUserInfo() {
        String userInfo = userInfoService.addUserInfo();
        return  "12313";
    }
}

package com.game.auth.api;

import com.game.auth.service.UserService;
import com.game.common.comman.api.BaseApi;
import com.game.common.comman.api.Result;
import com.game.common.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:25 2019/6/20 0020
 * @explain :  用户的api
 */
@RestController
@RequestMapping("/auth")
public class UserApi extends BaseApi {

    @Autowired
    private UserService userService;

    /**
     * @Author: wx
     * @Date  : 上午 10:25 2019/6/20 0020 
     * @params: 
     * @Desc  :  快速登录
     */
    @PostMapping("/fastLogin")
    public Result  fastLogin(@RequestParam(name = "uAccount") String uAccount,@RequestParam(name = "password") String passWord) {
        User user = userService.fastLogin(uAccount, passWord);
        return success("返回的用户信息",user);
    }
    
}

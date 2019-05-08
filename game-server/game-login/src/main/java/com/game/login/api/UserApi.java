package com.game.login.api;


import com.game.core.utils.api.BaseApi;
import com.game.core.utils.api.Result;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wx123
 * @since 2019-05-06
 */
@Controller
@RequestMapping("/user")
public class UserApi extends BaseApi {

    /**
     * @Author: wx
     * @Desc  :注册用户
     * @Date  : 下午 7:54 2019/5/6 0006
     * @params:
     */
    @RequestMapping("/register")
    public Result  register() {

        return null;
    }
    
}


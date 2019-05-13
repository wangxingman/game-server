package com.game.shop.api;

import com.game.shop.impl.service.UserInfoCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 2:26 2019/5/13 0013
 * @explain :
 */
@RestController
public class UserInfoCopyApi  {

    @Autowired
    private UserInfoCopyService userInfoService;
    
    /**
     * @Author: wx
     * @Date  : 下午 2:38 2019/5/13 0013 
     * @params: 
     * @Desc  :
     */
    @GetMapping("/removeUserInfo")
    public String  removeUserInfo() {
        String userInfo = userInfoService.removeUserInfo();
        return  "2313123";
    }
    
}

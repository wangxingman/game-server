package com.game.login.authentication.mobile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wx
 * @Date  : 下午 8:45 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 60;
    private String url = "/user1";
}

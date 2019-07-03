package com.game.login.authentication.mobile;

import lombok.Data;

/**
 * @Author: wx
 * @Date  : 下午 8:46 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
@Data
public class ValidateCodeProperties {

    private SmsCodeProperties sms = new SmsCodeProperties();
}

package com.game.login.authentication.mobile;

import com.game.login.properties.SecurityProperties;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wx
 * @Date  : 下午 8:45 2019/7/3 0003 
 * @params: 
 * @Desc  :  手机验证码
 */
@Data
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator{

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    public ValidateCode createImageCode(HttpServletRequest request, HttpServletResponse response) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());

    }
}

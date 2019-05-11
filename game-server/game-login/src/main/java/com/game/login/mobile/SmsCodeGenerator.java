package com.game.login.mobile;

import com.game.login.properties.SecurityProperties;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

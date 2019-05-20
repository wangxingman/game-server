package com.game.login.mobile;

import com.game.login.properties.SecurityProperties;
import com.game.login.redis.VcodeManager;
import com.game.login.service.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 4:47 2019/5/17 0017
 * @explain : 发送验证码
 */
@RestController
@Slf4j
public class SmsController {

    public static final String SESSION_KEY = "IMAGE_CODE";
    public static final String SESSION_SMS_KEY = "SMS_CODE";

    @Autowired
    private SecurityProperties securityProperties;


    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Autowired
    private VcodeManager vcodeManager;



    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        //生成随机验证码
        ValidateCode smsCode = smsCodeGenerator.createImageCode(request, response);

        //保存验证码，可以考虑用手机号保存
        //获取手机号
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");

        // 保存验证码
        vcodeManager.saveVcode(mobile, smsCode.getCode(), 5, TimeUnit.MINUTES);

        //验证码发送
        //SmsUtils.sendSms(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        /* SendSmsResponse sendSmsResponse = SmsUtils.sendSms(smsCode.getCode(), mobile);*/

        //模拟手机发送，此时调用短信服务商接口
        smsCodeSender.send(mobile,smsCode.getCode());
    }
}

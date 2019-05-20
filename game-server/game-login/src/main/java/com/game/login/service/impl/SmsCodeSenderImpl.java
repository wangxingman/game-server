package com.game.login.service.impl;

import com.game.login.service.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsCodeSenderImpl implements SmsCodeSender {
   /**
    * @Author: wx
    * @Date  : 下午 4:12 2019/5/20 0020 
    * @params: 
    * @Desc  : 手机发送验证码
    */
    @Override
    public void send(String mobile, String code) {
        log.info("向手机:"+mobile+"发送短信验证码:"+code);
    }
}

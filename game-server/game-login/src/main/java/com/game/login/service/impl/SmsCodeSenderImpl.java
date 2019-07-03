package com.game.login.service.impl;

import com.game.login.service.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: wx
 * @Date  : 下午 8:51 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
@Slf4j
@Service
public class SmsCodeSenderImpl implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        log.info("向手机:"+mobile+"发送短信验证码:"+code);
    }
}

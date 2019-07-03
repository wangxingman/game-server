package com.game.login.service;

/**
 * @Author: wx
 * @Date  : 下午 8:51 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
public interface SmsCodeSender {

    void send(String mobile, String code);
}

package com.game.login.service;

public interface SmsCodeSender {

    void send(String mobile, String code);
}

package com.game.auth.service;

import com.game.common.entity.email.EmailConfig;
import com.game.common.entity.email.EmailVo;
import org.springframework.scheduling.annotation.Async;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:26 2019/8/8 0008
 * @explain :
 */
public interface EmailService {

    /**
     * @Author: wx
     * @Date  : 上午 11:26 2019/8/8 0008 
     * @params: 
     * @Desc  :  查询发送人信息
     */
    EmailConfig find();
    
    /**
     * @Author: wx
     * @Date  : 上午 11:33 2019/8/8 0008 
     * @params: 
     * @Desc  :  发送邮件
     */
    @Async
    void send(EmailVo emailVo, EmailConfig emailConfig) throws Exception;
}

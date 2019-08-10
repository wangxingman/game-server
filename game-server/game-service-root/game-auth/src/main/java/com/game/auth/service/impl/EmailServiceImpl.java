package com.game.auth.service.impl;

import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import com.game.auth.repository.EmailRepository;
import com.game.auth.service.EmailService;
import com.game.common.entity.email.EmailConfig;
import com.game.common.entity.email.EmailVo;
import com.game.core.exception.BadRequestException;
import com.game.core.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:30 2019/8/8 0008
 * @explain :
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public EmailConfig find() {
        Optional<EmailConfig> emailConfig = emailRepository.findById(1L);
        if (emailConfig.isPresent()) {
            return emailConfig.get();
        } else {
            return new EmailConfig();
        }
    }

    @Override
    public void send(EmailVo emailVo, EmailConfig emailConfig) throws Exception {
        if (emailConfig == null) {
            throw new BadRequestException("请先配置，再操作");
        }
        MailAccount account = new MailAccount();
        account.setHost(emailConfig.getHost());
        account.setPort(Integer.parseInt(emailConfig.getPort()));
        account.setAuth(true);
        try {
            // 对称解密
            account.setPass(EncryptUtils.desDecrypt(emailConfig.getPass()));
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
        account.setFrom(emailConfig.getUser() + "<" + emailConfig.getFromUser() + ">");
        //ssl方式发送
        account.setStartttlsEnable(true);
        String content = emailVo.getContent();
        /**
         * 发送
         */
        try {
            Mail.create(account)
                    .setTos(emailVo.getTos().toArray(new String[emailVo.getTos().size()]))
                    .setTitle(emailVo.getSubject())
                    .setContent(content)
                    .setHtml(true)
                    //关闭session
                    .setUseGlobalSession(false)
                    .send();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}

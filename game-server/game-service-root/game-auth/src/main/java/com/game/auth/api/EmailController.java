package com.game.auth.api;

import com.game.auth.service.EmailService;
import com.game.common.comman.api.BaseApi;
import com.game.common.entity.email.EmailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:21 2019/8/8 0008
 * @explain :邮箱操作
 */
@RestController
@RequestMapping("email")
public class EmailController extends BaseApi {

    @Autowired
    private EmailService emailService;

    /**
     * @Author: wx
     * @Date : 上午 11:22 2019/8/8 0008
     * @params:
     * @Desc :  发送邮件
     */
    @PostMapping(value = "/sendEmail")
    public ResponseEntity send(@Validated @RequestBody EmailVo emailVo) throws Exception {
        emailService.send(emailVo, emailService.find());
        return new ResponseEntity(HttpStatus.OK);
    }

}

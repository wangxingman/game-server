package com.game.login.mobile;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 4:47 2019/5/17 0017
 * @explain :验证码格式
 */
@Data
public class ValidateCode implements Serializable {

    private String code;
    private LocalDateTime expireTime; //过期时间

    public ValidateCode(String code, int seconds) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(seconds);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}

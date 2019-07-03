package com.game.login.authentication.mobile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: wx
 * @Date  : 下午 8:45 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateCode implements Serializable {

    private String code;
    private LocalDateTime expireTime;

    public ValidateCode(String code, int seconds) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(seconds);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}

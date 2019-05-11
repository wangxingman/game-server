package com.game.login.mobile;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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

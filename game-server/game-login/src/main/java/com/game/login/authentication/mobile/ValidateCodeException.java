package com.game.login.authentication.mobile;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author: wx
 * @Date  : 下午 8:46 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super("校验失败");
    }
}

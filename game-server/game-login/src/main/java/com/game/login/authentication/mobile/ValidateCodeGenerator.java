package com.game.login.authentication.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lvhaibao
 * @description
 * @date 创建验证码接口
 */
public interface ValidateCodeGenerator {

    ValidateCode createImageCode(HttpServletRequest request, HttpServletResponse response);
}

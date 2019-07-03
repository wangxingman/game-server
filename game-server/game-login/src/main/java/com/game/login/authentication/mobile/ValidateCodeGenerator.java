package com.game.login.authentication.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wx
 * @Date  : 下午 8:46 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
public interface ValidateCodeGenerator {

    ValidateCode createImageCode(HttpServletRequest request, HttpServletResponse response);
}

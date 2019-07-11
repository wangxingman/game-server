package com.game.core.utils;

import com.game.common.dto.Login;
import com.game.common.entity.user.User;
import com.game.core.utils.web.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 7:46 2019/7/11 0011
 * @explain :
 */
@Component
public class LoginUtil {

    /**
     * 缓存处理器
     */
    @Autowired
    private HashOperations<String, Object, Object> hashOperations;

    /**
     * 缓存key
     */
    private static final String SINGLE_LOGIN = "SINGLE_LOGIN";

    /**
     * @Author: wx
     * @Date  : 下午 7:51 2019/7/11 0011 
     * @params: 
     * @Desc  : 添加用户缓存信息
     */
    public void add(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        String clientIp = RequestUtil.getClientIp(request);
        hashOperations.put(SINGLE_LOGIN, SecurityUtils.getUserId(), new Login(sessionId, clientIp));
    }

    /**
     * 是否已经登录
     *
     * @param request
     *            请求
     * @return 状态码 0 正常 其他http状态码
     */
    public static int checkLoginFail(HttpServletRequest request) {
        Long userId = SecurityUtils.getUserId();
        Login login = (Login) hashOperations.get(SINGLE_LOGIN, userId);
        String clientIp = RequestUtil.getClientIp(request);
        String sessionId = request.getSession().getId();
        String loginClientIp = login.getClientIp();
        String loginSessionId = login.getSessionId();
        if (loginClientIp.equals(clientIp)) {
            if (!loginSessionId.equals(sessionId)) {
                return HttpStatus.UNAUTHORIZED.value();
            }
        } else {
            return HttpStatus.NOT_ACCEPTABLE.value();
        }
        return 0;
    }

}

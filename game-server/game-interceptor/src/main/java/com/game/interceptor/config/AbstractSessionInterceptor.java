package com.game.interceptor.config;

import com.game.common.comman.SessionUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:51 2019/7/23 0023
 * @explain :
 */
public abstract class AbstractSessionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SessionUtil.set(getSession(request, response, handler));
        return true;
    }

    /**
     * @Author: wx
     * @Date  : 下午 2:55 2019/7/23 0023 
     * @params: 
     * @Desc  :
     */
    public abstract SessionUtil.Session getSession(
            HttpServletRequest request, HttpServletResponse response, Object handler
    );

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SessionUtil.destory();
    }
}

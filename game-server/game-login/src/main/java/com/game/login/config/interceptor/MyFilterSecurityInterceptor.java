package com.game.login.config.interceptor;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Auther : wx
 * @Desc :   过滤用户请求
 * @Date :  下午 8:25 2019/5/9 0009
 * @explain: 继承AbstractSecurityInterceptor、实现Filter是必须的
 *               首先，登陆后，每次访问资源都会被这个拦截器拦截，会执行doFilter这个方法，这个方法调用了invoke方法，其中fi断点显示是一个url
 *             最重要的是beforeInvocation这个方法，它首先会调用MyInvocationSecurityMetadataSource类的getAttributes方法获取被拦截url所需的权限
 *             在调用MyAccessDecisionManager类decide方法判断用户是否具有权限,执行完后就会执行下一个拦截器
 */
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return null;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return null;
    }
}

package com.game.core.jwt;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther : wx
 * @Desc :   jwt认证token
 * @Date :  下午 8:39 2019/5/9 0009
 * @explain : 次请求接口时 就会进入这里验证token 是否合法
 *  *             token 如果用户一直在操作，则token 过期时间会叠加
 *                如果超过设置的过期时间未操作  则token 失效 需要重新登录
 */
public class JwtAuthenticationTokenFilter  extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        
    }
}

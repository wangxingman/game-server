package com.game.login.authentication.mobile;

import com.game.common.constant.Const;
import com.game.login.handler.MyAuthenticationFailureHandler;
import com.game.login.properties.SecurityProperties;
import com.game.login.redis.VcodeManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: wx
 * @Date  : 下午 12:07 2019/6/9 0009 
 * @params: 
 * @Desc  :验证码的过滤器
 */
@Slf4j
@Data
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 验证码校验失败处理器
     */
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    private Set<String> urls = new HashSet<>();
    private SecurityProperties securityProperties;

    private VcodeManager vcodeManager;

    public static final String ENCRYPT = "a3caed36f0fe5a01e5f144db8927235e";
    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();


    public SmsCodeFilter(VcodeManager vcodeManager){
        this.vcodeManager = vcodeManager;
    }


    /**
     * 初始化要拦截的url配置信息
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(
                securityProperties.getCode().getSms().getUrl(), ",");
        for (String configUrl : configUrls) {
            urls.add(configUrl);
        }
        urls.add("/authentication/mobile");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String requestHeader = request.getHeader(Const.Header.TOKEN);

        boolean action = false;
        for (String url : urls) {
            if (pathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }

        String username = null;
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);
            String[] split = requestHeader.split("-");
            username = split[1];
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            logger.info("用户名字"+username);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else {
            if (action) {
                try {
                    redisValidate(new ServletWebRequest(request));
                } catch (ValidateCodeException e) {
                    myAuthenticationFailureHandler.onAuthenticationFailure(request, response, e);
                    e.printStackTrace();
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    private void redisValidate(ServletWebRequest request) throws ServletRequestBindingException {

        String mobile = request.getParameter("mobile");
        log.info(mobile);

        System.out.println( vcodeManager.getVcode(mobile));
        if (null == vcodeManager.getVcode(mobile)) {
            throw new ValidateCodeException("验证码已过期" + mobile);
        }

        String codeInRedis = vcodeManager.getVcode(mobile).toString();
        //从请求中拿到imageCode这个参数
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");
        if (!codeInRedis.equals(codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        vcodeManager.removeVcode(mobile);
    }
}

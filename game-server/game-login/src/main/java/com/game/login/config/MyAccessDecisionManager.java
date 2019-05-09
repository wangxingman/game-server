package com.game.login.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Auther: wx
 * @Desc : 资源权限认证器 认证用户是否拥有所请求资源的权限
 * @Date : 下午 8:09 2019/5/9 0009
 */
@Slf4j
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
     * @Author: wx
     * @Desc  :
     * @Date  : 下午 8:12 2019/5/9 0009 
     * @params: authentication 装载了从数据库读出来的权限(角色) 数据。
     *          这里是从MyUserDetailService里的loadUserByUsername
     *          方法里的grantedAuths对象的值传过来给 authentication 对象,
     *          简单点就是从spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息
     * @params: object url
     * @params: configAttributes 装载了请求的url允许的角色数组 。
     *          这里是从MyInvocationSecurityMetadataSource里的loadResourceDefine
     *          方法里的atts对象取出的角色数据赋予给了configAttributes对象
     *
     *          注意： Authentication authentication 如果是前后端分离 则有跨域问题，
     *          跨域情况下 authentication 无法获取当前登陆人的身份认证(登陆成功后)，我尝试用token来效验权限
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        if(CollectionUtils.isEmpty(collection)){   // 无权限访问
            log.info("无访问权限.");
            throw new AccessDeniedException("无访问权限.");
        }
        Iterator<ConfigAttribute> iterator = collection.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            String needRole = configAttribute.getAttribute();
            for(GrantedAuthority grantedAuthority : authentication.getAuthorities()){
                //grantedAuthority 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
                //判断两个请求的url的权限和用户具有的权限是否相同，如相同，允许访问 权限就是那些以ROLE_为前缀的角色
                if (needRole.trim().equals(grantedAuthority.getAuthority().trim())){
                    //匹配到对应的角色，则允许通过
                    return;
                }
            }
        }
        //该url具有访问权限，但是当前登录用户没有匹配到URL对应的权限，则抛出无权限错误
        log.info("无访问权限.");
        throw  new AccessDeniedException("无访问权限.");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

package com.game.login.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Auther: wx
 * @Desc :  加载资源与权限的对应关系
 * @Date : 下午 8:21 2019/5/9 0009
 * @explain 实现FilterInvocationSecurityMetadataSource接口也是必须的。 首先，这里从数据库中获取信息。 其中loadResourceDefine方法不是必须的，
 *            这个只是加载所有的资源与权限的对应关系并缓存起来，避免每次获取权限都访问数据库（提高性能），然后getAttributes根据参数（被拦截url）返回权限集合。
 *             这种缓存的实现其实有一个缺点，因为loadResourceDefine方法是放在构造器上调用的，而这个类的实例化只在web服务器启动时调用一次，那就是说loadResourceDefine方法只会调用一次，
 *            如果资源和权限的对应关系在启动后发生了改变，那么缓存起来的权限数据就和实际授权数据不一致，那就会授权错误了。但如果资源和权限对应关系是不会改变的，这种方法性能会好很多。
 *            要想解决 权限数据的一致性 可以直接在getAttributes方法里面调用数据库操作获取权限数据，通过被拦截url获取数据库中的所有权限，封装成Collection<ConfigAttribute>返回就行了。（灵活、简单
 *             器启动加载顺序：1：调用loadResourceDefine()方法  2：调用supports()方法   3：调用getAllConfigAttributes()方法
 */
@Slf4j
@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}

package com.game.core.jwt;



/**
 * @Auther : wx
 * @Desc :   jwt用户信息
 * @Date :  下午 8:36 2019/5/9 0009
 * @explain : Spring Security需要我们实现几个东西，第一个是UserDetails：
 *             这个接口中规定了用户的几个必须要有的方法，所以我们创建一个JwtUser类来实现这个接口。
 *             为什么不直接使用User类？因为这个UserDetails完全是为了安全服务的，
 *             它和我们的领域类可能有部分属性重叠，但很多的接口其实是安全定制的，所以最好新建一个类：
 */
public class JWTUserDetails {
}

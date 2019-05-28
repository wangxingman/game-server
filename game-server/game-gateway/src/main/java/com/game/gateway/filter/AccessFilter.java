package com.game.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 7:03 2019/5/28 0028
 * @explain :
 */
@Slf4j
@Component
public class AccessFilter  extends ZuulFilter {

    /**
     * @Author: wx
     * @Date  : 下午 7:05 2019/5/28 0028
     * @params:
     * @Desc  :    ////过滤器的类命型，它决定过滤器在请求的哪个生周期中执行，这里定义为pre，代表会在请求被理由之前执行。
     *     //PRE：这种过滤器在请求被路由之前调用。我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:05 2019/5/28 0028
     * @params:
     * @Desc  : 过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:05 2019/5/28 0028
     * @params:
     * @Desc  : //判断该过滤器是否需要被执行。这里我们直接返回了true，因此该过滤器对所有的请求都生效。实际运行中我们可以利用该函数
     *     //来指定过滤器的有效范围。
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:05 2019/5/28 0028
     * @params:
     * @Desc  :  过滤器的具体执行逻辑。
     */
    @Override
    public Object run() throws ZuulException {
        // RequestContext 来进行数据的传递
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestURI = request.getRequestURI();
        System.out.println("requestURI请求的url+"+requestURI);
        log.info("过滤器已经拦截ok");
        return null;
    }
}

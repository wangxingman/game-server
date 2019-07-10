package com.game.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.game.common.constant.Const;
import com.game.core.ws.clientconfig.WsSyncClient;
import com.game.core.ws.dto.MessageType;
import com.game.core.ws.dto.NetMessage;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 7:03 2019/5/28 0028
 * @explain :  zuul的过滤器
 */
@Slf4j
@Component
public class AccessFilter extends ZuulFilter {

    /**
     * @Author: wx
     * @Date : 下午 7:05 2019/5/28 0028
     * @params:
     * @Desc :
     * <p>
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * @Author: wx
     * @Date : 下午 7:05 2019/5/28 0028
     * @params:
     * @Desc : 过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * @Author: wx
     * @Date : 下午 7:05 2019/5/28 0028
     * @params:
     * @Desc : //判断该过滤器是否需要被执行。这里我们直接返回了true，因此该过滤器对所有的请求都生效。实际运行中我们可以利用该函数
     * //来指定过滤器的有效范围。
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * @Author: wx
     * @Date : 下午 7:05 2019/5/28 0028
     * @params:
     * @Desc :  过滤器的具体执行逻辑。
     */
    @Override
    public Object run() throws ZuulException {
        // RequestContext 来进行数据的传递
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        //todo 用户传入的token 和对应的比较
        String accessToken = request.getParameter("token");
        if(Objects.isNull(accessToken)) {
            accessToken =request.getHeader("token");
        }
        String url = request.getRequestURL().toString();

        if (url.contains("websocket") && Objects.nonNull(accessToken)) {
            MessageType messageType = new MessageType(Const.number.THREE);
            NetMessage netMessage = NetMessage.builder()
                    .messageType(messageType)
                    .bytes((JSONObject.toJSONString(accessToken).getBytes()))
                    .build();
            //接收消息的时候 我去初始化的客户的数据
            String s = WsSyncClient.sendMsgToGame(netMessage, Const.Addr.GATE_WAY);
            JSONObject res = JSONObject.parseObject(s);
            JSONObject resData = JSONObject.parseObject(res.getString("data"));
            //对请求进行路由
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            try {
                ctx.getResponse().getWriter().write(resData.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resData.toJSONString();
        } else if (Objects.nonNull(accessToken)) {
            log.info("这是web请求!");
            //对请求进行路由
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        } else {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            } catch (Exception e) {

            }
        }
        return null;
    }


}

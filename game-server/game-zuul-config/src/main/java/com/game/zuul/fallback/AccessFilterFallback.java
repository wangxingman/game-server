package com.game.zuul.fallback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.game.common.comman.api.Result;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:28 2019/6/15 0015
 * @explain :路由熔断
 */
@EqualsAndHashCode
@Component
@NoArgsConstructor
public class AccessFilterFallback implements FallbackProvider {

    private static final byte[] ERROR_JSON_RESULT = JSON.toJSONString(new Result(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "服务器异常"
    )).getBytes();

    /**
     * @Author: wx
     * @Date  : 下午 4:41 2019/7/5 0005 
     * @params: 
     * @Desc  :   Zuul它是负责哪个route定义的熔断 【这里使用*表示所有】
     */
    @Override
    public String getRoute() {
        //定义回退的id
        return "*";
    }

    /**
     * @Author: wx
     * @Date  : 下午 4:42 2019/7/5 0005 
     * @params: 
     * @Desc  :  Zuul 断路出现时，它会提供一个什么返回值来处理请求
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {

            /**
             * @Author: wx
             * @Date  : 下午 4:48 2019/7/5 0005 
             * @params: 
             * @Desc  :服务出现异常 返回消息
             */
            @Override
            public InputStream getBody() throws IOException {
                JSONObject r = new JSONObject();
                r.put("state", "9999");
                r.put("msg", "系统错误，请求失败");
                return new ByteArrayInputStream(r.toJSONString().getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                //和body中的内容编码一致，否则容易乱码
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }

            /**
             * 网关向api服务请求是失败了，但是消费者客户端向网关发起的请求是OK的，
             * 不应该把api的404,500等问题抛给客户端
             * 网关和api服务集群对于客户端来说是黑盒子
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {
            }
        };
    }

}

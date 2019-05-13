package com.game.core.utils.api;

import com.alibaba.fastjson.JSON;
import com.game.common.comman.Default;
import com.game.common.comman.HttpCode;
import com.game.core.utils.web.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 7:44 2019/5/6 0006
 */
@Slf4j
public class BaseApi {

    /**
     * getRequest
     *
     * @return request
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * getResponse
     *
     * @return response
     */
    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 默认成功返回
     *
     * @return Result
     */
    public Result success() {
        return success(null, null);
    }

    /**
     * 带信息成功返回
     *
     * @param message 消息
     * @return Result
     */
    public Result success(String message) {
        return success(message, null);
    }

    /**
     * 带数据成功返回
     *
     * @param data 数据
     * @return Result
     */
    public Result success(Object data) {
        return success(null, data);
    }

    /**
     * 带String数据成功返回
     *
     * @param data 数据
     * @return Result
     */
    public Result successStr(String data) {
        return success(null, data);
    }

    /**
     * 带数据成功返回
     *
     * @param data 数据
     * @return Result
     */
    public Result successEager(Object data) {
        return success(null, JSON.toJSON(data));
    }

    /**
     * 默认失败返回
     *
     * @return Result
     */
    public Result error() {
        return error(null, null);
    }

    /**
     * 带信息失败返回
     *
     * @param message 消息
     * @return Result
     */
    public Result error(String message) {
        return error(message, null);
    }

    /**
     * 带数据失败返回
     *
     * @param data 数据
     * @return Result
     */
    public Result error(Object data) {
        return error(null, data);
    }

    /**
     * 带String数据失败返回
     *
     * @param data 数据
     * @return Result
     */
    public Result errorStr(String data) {
        return error(null, data);
    }

    /**
     * 带数据失败返回
     *
     * @param data 数据
     * @return Result
     */
    public Result errorEager(Object data) {
        return error(null, JSON.toJSON(data));
    }

    /**
     * 标准成功返回
     *
     * @param message 消息
     * @param data    数据
     * @return Result
     */
    public Result success(String message, Object data) {
        return success(HttpCode.SUCCESS, message, data);
    }

    /**
     * 完整成功返回
     *
     * @param code    标识码
     * @param message 消息
     * @param data    数据
     * @return Result
     */
    public Result success(int code, String message, Object data) {
        return new Result(code, true, message, data);
    }

    /**
     * 标准失败返回
     *
     * @param message 消息
     * @param data    数据
     * @return Result
     */
    public Result error(String message, Object data) {
        return error(HttpCode.FAIL, message, data);
    }

    /**
     * 完整失败返回
     *
     * @param code    标识码
     * @param message 消息
     * @param data    数据
     * @return Result
     */
    public Result error(int code, String message, Object data) {
        return new Result(code, false, message, data);
    }

    /**
     * Get cookie value by cookie name.
     *
     * @param name         名称
     * @param defaultValue 默认值
     * @return cookie
     */
    public String getCookie(String name, String defaultValue) {
        Cookie cookie = getCookieObject(name);
        return cookie != null ? cookie.getValue() : defaultValue;
    }

    /**
     * Get cookie value by cookie name.
     *
     * @param name 名称
     * @return cookie
     */
    public String getCookie(String name) {
        return getCookie(name, null);
    }

    /**
     * Get cookie value by cookie name and convert to Integer.
     *
     * @param name 名称
     * @return cookie
     */
    public Integer getCookieToInt(String name) {
        String result = getCookie(name);
        return result != null ? Integer.parseInt(result) : null;
    }

    /**
     * Get cookie value by cookie name and convert to Integer.
     *
     * @param name         名称
     * @param defaultValue 默认值
     * @return cookie
     */
    public Integer getCookieToInt(String name, Integer defaultValue) {
        String result = getCookie(name);
        return result != null ? Integer.parseInt(result) : defaultValue;
    }

    /**
     * Get cookie value by cookie name and convert to Long.
     *
     * @param name 名称
     * @return cookie
     */
    public Long getCookieToLong(String name) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : null;
    }

    /**
     * Get cookie value by cookie name and convert to Long.
     *
     * @param name         名称
     * @param defaultValue 默认值
     * @return cookie
     */
    public Long getCookieToLong(String name, Long defaultValue) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : defaultValue;
    }

    /**
     * Get cookie object by cookie name.
     *
     * @param name 名称
     * @return cookie
     */
    public Cookie getCookieObject(String name) {
        Cookie[] cookies = getRequest().getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * Get all cookie objects.
     *
     * @return cookie
     */
    public Cookie[] getCookieObjects() {
        Cookie[] result = getRequest().getCookies();
        return result != null ? result : new Cookie[0];
    }

    /**
     * Set Cookie.
     *
     * @param name            cookie name
     * @param value           cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.
     *                        n&gt;0 : max age in n seconds.
     * @param isHttpOnly      true if this cookie is to be marked as HttpOnly, false otherwise
     */
    public void setCookie(String name, String value, int maxAgeInSeconds, boolean isHttpOnly) {
        doSetCookie(name, value, maxAgeInSeconds, null, null, isHttpOnly);
    }

    /**
     * Set Cookie.
     *
     * @param name            cookie name
     * @param value           cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.
     *                        n&gt;0 : max age in n seconds.
     */
    public void setCookie(String name, String value, int maxAgeInSeconds) {
        doSetCookie(name, value, maxAgeInSeconds, null, null, null);
    }

    /**
     * Set Cookie to response.
     *
     * @param response 响应
     * @param cookie   cookie
     */
    public void setCookie(HttpServletResponse response, Cookie cookie) {
        response.addCookie(cookie);
    }

    /**
     * Set Cookie to response.
     *
     * @param name            cookie name
     * @param value           cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.
     *                        n&gt;0 : max age in n seconds.
     * @param path            see Cookie.setPath(String)
     * @param isHttpOnly      true if this cookie is to be marked as HttpOnly, false otherwise
     */
    public void setCookie(String name, String value, int maxAgeInSeconds, String path, boolean isHttpOnly) {
        doSetCookie(name, value, maxAgeInSeconds, path, null, isHttpOnly);
    }

    /**
     * Set Cookie to response.
     *
     * @param name            cookie name
     * @param value           cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.
     *                        n&gt;0 : max age in n seconds.
     * @param path            see Cookie.setPath(String)
     */
    public void setCookie(String name, String value, int maxAgeInSeconds, String path) {
        doSetCookie(name, value, maxAgeInSeconds, path, null, null);
    }

    /**
     * Set Cookie to response.
     *
     * @param name            cookie name
     * @param value           cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.
     *                        n&gt;0 : max age in n seconds.
     * @param path            see Cookie.setPath(String)
     * @param domain          the domain name within which this cookie is visible; form is
     *                        according to RFC 2109
     * @param isHttpOnly      true if this cookie is to be marked as HttpOnly, false otherwise
     */
    public void setCookie(
            String name, String value, int maxAgeInSeconds, String path, String domain, boolean isHttpOnly
    ) {
        doSetCookie(name, value, maxAgeInSeconds, path, domain, isHttpOnly);
    }

    /**
     * Remove Cookie.
     *
     * @param name 名称
     */
    public void removeCookie(String name) {
        doSetCookie(name, null, 0, null, null, null);
    }

    /**
     * Remove Cookie.
     *
     * @param name 名称
     * @param path 路径
     */
    public void removeCookie(String name, String path) {
        doSetCookie(name, null, 0, path, null, null);
    }

    /**
     * Remove Cookie.
     *
     * @param name   名称
     * @param path   路径
     * @param domain 域名
     */
    public void removeCookie(String name, String path, String domain) {
        doSetCookie(name, null, 0, path, domain, null);
    }

    /**
     * cookie统一设置类
     *
     * @param name            cookie名字
     * @param value           cookie值
     * @param maxAgeInSeconds 过期时间单位秒
     * @param path            存储路径默认/
     * @param domain          域
     * @param isHttpOnly      是否只读
     */
    private void doSetCookie(
            String name, String value, int maxAgeInSeconds, String path, String domain, Boolean isHttpOnly
    ) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAgeInSeconds);
        // set the default path value to "/"
        if (path == null) {
            path = "/";
        }
        cookie.setPath(path);

        if (domain != null) {
            cookie.setDomain(domain);
        }
        if (isHttpOnly != null) {
            cookie.setHttpOnly(isHttpOnly);
        }
        getResponse().addCookie(cookie);
    }

    /**
     * 跳转
     *
     * @param view 跳转地址
     * @return 跳转
     */
    public String forward(String view) {
        return "forward:" + view;
    }

    /**
     * 重定向
     *
     * @param url 地址
     * @return 重定向
     */
    public String redirect(String url) {
        return "redirect:" + url;
    }

    /**
     * AJAX校验异常处理
     *
     * @param result 异常结果
     * @return 异常消息
     */
    public Result validError(BindingResult result) {
        List<FieldError> errors = result.getFieldErrors();
        FieldError fieldError = errors.get(0);
        return error(fieldError.getDefaultMessage());
    }

    /**
     * 获取客户端ip
     *
     * @return
     */
    public String getClientIp() {
        return RequestUtil.getClientIp(getRequest());
    }

    /**
     * 打印json
     *
     * @param json json字符串
     * @throws IOException
     */
    public void writeJson(String json) {
        HttpServletResponse response = getResponse();
        response.setHeader("Content-type", "text/html;charset=" + Default.ENCODING);
        response.setCharacterEncoding(Default.ENCODING);
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.print(json);
        } catch (IOException e) {
            log.error("error response", e);
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}

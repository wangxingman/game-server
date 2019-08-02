package com.game.interceptor.config;

import com.game.common.comman.UrlFormat;
import com.game.common.comman.api.Result;
import com.game.common.constant.Const;
import com.game.common.entity.user.User;
import com.game.common.redis.RedisUtil;
import com.game.common.comman.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:50 2019/7/23 0023
 * @explain :
 */
public class SessionInterceptor extends AbstractSessionInterceptor {

    @Override
    public SessionUtil.Session getSession(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session=request.getSession();
        Object obj=session.getAttribute("user");
        if(obj!=null){
            return (SessionUtil.Session)obj;
        }else{
            String header = UrlFormat.decode(request.getHeader(Const.Header.CURRENT_USER_REAL_NAME)) ;
            if(Objects.isNull(header)){
               return Result.builder().code(10).data("头信息有误！").build();
            }else {
                //反序列化buf
                User user = (User) RedisUtil.getObj(header);
                if (Objects.isNull(user)) {
                    return Result.builder().code(10).data("缓存没有该信息！").build();
                }
                session.setAttribute("user", user);
                return  Result.builder().code(200).data(user).build();
            }
        }
    }
    
}

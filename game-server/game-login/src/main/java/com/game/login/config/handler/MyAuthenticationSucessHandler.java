package com.game.login.config.handler;

import com.alibaba.fastjson.JSONObject;
import com.game.common.Const.Const;
import com.game.common.entity.user.User;
import com.game.common.mapper.UserMapper;
import com.game.core.jwt.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义成功登陆成功逻辑
 */

@Component
public class MyAuthenticationSucessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
//        UserDetails principal = (UserDetails)authentication.getPrincipal();
//        System.out.println("principal+++"+principal);
//        String username = principal.getUsername();
//        User user = userMapper.findByUName(username);
//
//        String token = null;
//        try {
//            token = ASE.AESEncode(mConst.ASE, user.getUAccount());
//            user.setUToken(token);
//            userMapper.saveAndFlush(user);
//            RedisUtil.save("uid_"+user.getUAccount(), JSONObject.toJSONString(user),5L);
//            Token tokenCopy = new Token().setUid(user.getUId()).setLoginTime(1L).setSeries(username).setToken(token);
//            RedisUtil.save("token_"+token, JSONObject.toJSONString(tokenCopy),1L);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        HttpSession session = request.getSession();
//        session.setAttribute(mConst.UserMessage.USER_SESSION, user);
//        session.setMaxInactiveInterval(2 * 3600);  //SessionUtil.Session session = SessionUtil.get();
//        Cookie cookie = new Cookie("token_",token);
//        cookie.setMaxAge(2 * 3600);  // 客户端的JSESSIONID也保存两小时
//        cookie.setPath("/");
//        response.addCookie(cookie);
//        response.addHeader("token",token);
//
//        HttpHeaders httpHeaders = new HttpHeaders();
     /*   httpHeaders.add("dsa","fa");
     *    redirectStrategy.sendRedirect(request, response, "/index");
     * */
        
        UserDetails principal = (UserDetails)authentication.getPrincipal();
        String username = principal.getUsername();
        User user = userMapper.selectOne(User.builder().vName(username).build());
        if(user != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("uid",user.getVId());
            map.put("sta",user.getVCreatetime());
            map.put("end",new Date().getTime()+ Const.date.HALF_HOUR); //半个小时之后
            JwtToken.creatToken(map);
        }
    }
}

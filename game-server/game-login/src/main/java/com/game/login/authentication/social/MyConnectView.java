package com.game.login.authentication.social;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: wx
 * @Date  : 下午 8:48 2019/7/3 0003
 * @params:
 * @Desc  : 绑定解绑页面提示
 */
@Slf4j
public class MyConnectView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {



        response.setContentType("text/html;charset=UTF-8");
        if (model.get("connections") == null) {
            log.info("解绑成功");
            response.getWriter().write("<h3>解绑成功</h3>");
        } else {
            log.info("绑定成功");
            response.getWriter().write("<h3>绑定成功</h3>");
        }

    }
}

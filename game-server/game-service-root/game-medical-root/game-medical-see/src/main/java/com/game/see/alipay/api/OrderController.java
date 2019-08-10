package com.game.see.alipay.api;

import com.game.see.alipay.AlipayBean;
import com.game.see.alipay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:47 2019/8/10 0010
 * @explain :
 */
@RestController()
@RequestMapping("order")
public class OrderController {

    @Autowired
    private PayService payService;

    /**
     * @Author: wx
     * @Date : 下午 2:51 2019/8/10 0010
     * @params:
     * @Desc :  测试阿里支付
     */
    @GetMapping(value = "alipay")
    public String alipay() {
        String outTradeNo = "dzcp100010001";
        String subject = "红魔手机";
        String totalAmount = "0.1";
        String body = "红魔手机 努比亚出品游戏手机";
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setOut_trade_no(outTradeNo);
        alipayBean.setSubject(subject);
        alipayBean.setTotal_amount(totalAmount);
        alipayBean.setBody(body);
        return payService.aliPay(alipayBean);
    }
}

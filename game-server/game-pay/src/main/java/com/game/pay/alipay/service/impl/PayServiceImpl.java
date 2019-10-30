package com.game.pay.alipay.service.impl;

import com.game.pay.alipay.Alipay;
import com.game.pay.alipay.AlipayBean;
import com.game.pay.alipay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:48 2019/8/10 0010
 * @explain :
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private Alipay alipay;

    @Override
    public String aliPay(AlipayBean alipayBean)  {
        return alipay.pay(alipayBean);
    }
}

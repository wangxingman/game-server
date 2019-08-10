package com.game.see.alipay.service.impl;

import com.game.see.alipay.Alipay;
import com.game.see.alipay.AlipayBean;
import com.game.see.alipay.service.PayService;
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

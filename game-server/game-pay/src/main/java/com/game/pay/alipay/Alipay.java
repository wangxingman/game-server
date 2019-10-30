package com.game.pay.alipay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.game.pay.alipay.model.AlipayPropertiesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:39 2019/8/10 0010
 * @explain :
 */
@Component
public class Alipay {

    @Autowired
    private AlipayPropertiesModel alipayPropertiesModel;
    /**
     * @Author: wx
     * @Date : 下午 2:41 2019/8/10 0010
     * @params:
     * @Desc : 支付接口
     */
    public String pay(AlipayBean alipayBean) {
        //获取初始参数
        String result = null;
        try {
            String serverUrl = alipayPropertiesModel.getGatewayUrl();
            String appId = alipayPropertiesModel.getAppId();
            String privateKey = alipayPropertiesModel.getPrivateKey();
            String format = "json";
            String charset = alipayPropertiesModel.getCharset();
            String alipayPublicKey = alipayPropertiesModel.getPublicKey();
            String signType = alipayPropertiesModel.getSignType();
            String returnUrl = alipayPropertiesModel.getReturnUrl();
            String notifyUrl = alipayPropertiesModel.getNotifyUrl();

            AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
            // 2、设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            // 页面跳转同步通知页面路径
            alipayRequest.setReturnUrl(returnUrl);
            // 服务器异步通知页面路径
            alipayRequest.setNotifyUrl(notifyUrl);
            // 封装参数
            alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
            // 3、请求支付宝进行付款，并获取支付结果
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        // 返回付款信息
        return result;
    }
}

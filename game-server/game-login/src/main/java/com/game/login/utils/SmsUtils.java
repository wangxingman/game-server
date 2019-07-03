package com.game.login.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 8:40 2019/7/3 0003
 * @explain :
 */
public class SmsUtils {

    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     * */
    static final String product = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     * */
    static final String domain = "dysmsapi.aliyuncs.com";

    static final String accessKeyId = "LTAI1Vm7JTL8CSPf";
    static final String accessKeySecret = "jMpy3B5MjhYYP0M5Upg16CyvGRBGgt";


    /**
     * 阿里的发送短信
     * @param param
     * @param pahoneNumber
     * @return
     * @throws Exception
     */
    public static SendSmsResponse sendSms(String param,String pahoneNumber) throws  Exception{
        IClientProfile profile = DefaultProfile.getProfile("cn-wuhan", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-wuhan","cn-wuhan", product, domain);
        IAcsClient defaultAcsClient = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(pahoneNumber);
        //审核通过的签名
        request.setSignName("阮威敏");
        request.setTemplateCode("SMS_119920792");
        //模板中的变量替换json,如模板内容为${code}
        request.setTemplateParam("{\"code\":\""+ param +"\"}");

        return defaultAcsClient.getAcsResponse(request);
    }

}

package com.game.login.redis;

import java.util.concurrent.TimeUnit;

/**
 * @Author: wx
 * @Date  : 下午 8:50 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
public interface VcodeManager {


    /**
     * 生成验证码
     *
     * @return 验证码
     */
    String generateVcode();



    /**
     * 保存验证码
     *
     * @param mobile     手机号
     * @param code       验证码
     * @param expireTime 保存时间
     * @param unit       保存得单位时间
     */
    void saveVcode(String mobile, String code, long expireTime, TimeUnit unit);

    /**
     * 获取验证码
     *
     * @param mobile 手机号
     * @return 验证码
     */
    Object getVcode(String mobile);


    /**
     * 删除验证码
     *
     * @param mobile
     */
    void removeVcode(String mobile);
}

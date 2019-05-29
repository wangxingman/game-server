package com.game.common.Const;

import lombok.AllArgsConstructor;

/**
 * @Auther: wx
 * @Desc : 定义各种返回报错类型
 * @Date : 下午 8:07 2019/5/8 0008
 */
@AllArgsConstructor
public enum Errors {

    /**
     * @Author: wx
     * @Desc  : 枚举里面定义的参数
     * @Date  : 下午 8:09 2019/5/8 0008 
     * @params: 
     */
    no_charge_error(3091,"请充值后游戏1"),

    no_false_error(3092,"您发送的数据不正确！");

    
    
    private int code;
    private String msg;
}

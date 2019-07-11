package com.game.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 7:44 2019/7/11 0011
 * @explain :
 */
@Data
@AllArgsConstructor
public class Login {
    /**
     * 序列号唯一id
     */
    private static final long serialVersionUID = -4557183502959406223L;
    /**
     * sessionId
     */
    private String sessionId;
    /**
     * 客户机ip
     */
    private String clientIp;
    
}

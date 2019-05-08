package com.game.common.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;

/**
 * @Auther: wx
 * @Desc:
 * @Date: 下午 4:35 2019/5/5 0005
 */
//获取复杂的结构方式 不复杂可以直接用value
@Component
@ConfigurationProperties(prefix = "params")
@Data
public class NettyParams {

    public  Integer port;

    public Map<Integer, String> localMap;
    
}

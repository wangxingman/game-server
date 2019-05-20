package com.game.hall.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 9:56 2019/5/20 0020
 * @explain : yml文件的启动的netty服务器属性
 */
@Component
@ConfigurationProperties(prefix = "hall")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HallProperties implements Serializable {

    /**
     * 地址
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    /**
     * 心态检测时间(ms)
     */
    private long checkTime;

}

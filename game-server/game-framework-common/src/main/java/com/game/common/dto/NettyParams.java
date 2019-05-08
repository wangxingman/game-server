package com.game.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: wx
 * @Desc:
 * @Date: 下午 4:35 2019/5/5 0005
 */
//获取复杂的结构方式 不复杂可以直接用value
@Component
@ConfigurationProperties(prefix = "params")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NettyParams {

    private Integer port;


}

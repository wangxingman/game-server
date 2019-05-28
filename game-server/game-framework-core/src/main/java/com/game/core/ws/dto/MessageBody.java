package com.game.core.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 5:37 2019/5/17 0017
 * @explain : 封装存储的数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageBody {
    /**
     * 存储数据
     */
    private byte[] bytes;

}

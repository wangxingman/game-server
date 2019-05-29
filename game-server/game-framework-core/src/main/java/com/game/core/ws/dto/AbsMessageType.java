package com.game.core.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 2:58 2019/5/29 0029
 * @explain :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbsMessageType {
    /**魔法头*/
    private short head;

    /**长度*/
    private int length;

    /**版本号*/
    private byte version;

    /**序列号*/
    private int serial;
}

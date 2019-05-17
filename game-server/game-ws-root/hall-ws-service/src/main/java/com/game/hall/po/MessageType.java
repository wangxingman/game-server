package com.game.hall.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 5:11 2019/5/17 0017
 * @explain :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageType {

    /**魔法头*/
    private short head;

    /**长度*/
    private int length;

    /**版本号*/
    private byte version;

    /**命令*/
    private  short cmd;

    /**序列号*/
    private int serial;
}

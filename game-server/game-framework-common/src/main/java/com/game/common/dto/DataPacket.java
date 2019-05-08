package com.game.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: wx
 * @Desc :  客户端发送消息封装
 * @Date : 下午 8:49 2019/5/5 0005
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPacket {
    private int AgreementNo;  //协议号
    private String data;     //发送的内容
}

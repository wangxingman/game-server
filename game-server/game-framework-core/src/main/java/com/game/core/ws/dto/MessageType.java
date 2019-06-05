package com.game.core.ws.dto;

import com.game.common.Const.Const;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 5:11 2019/5/17 0017
 * @explain :
 */
@Data
@Builder
@Accessors
public class MessageType{

    /**命令*/
    private int cmd;

    /**基础类型*/
    private  AbsMessageType absMessageType;

    public MessageType(int cmd) {
        AbsMessageType absMessageType = AbsMessageType.builder()
                .version((byte) Const.number.THREE)
                .serial(Const.number.FOUR)
                .head((short) Const.number.FOUR)
                .length(Const.number.FIVE)
                .build();
        this.absMessageType = absMessageType;
        this.cmd =  cmd;
    }
}

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
@Accessors
public class MessageType{

    /**命令*/
    private Integer cmd;

    /**基础类型*/
    private  AbsMessageType absMessageType;

    public MessageType() {
    }

    public MessageType(Integer cmd) {
        //想办法初始化  就在加载 每次都 加载一个对象 浪费内存
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

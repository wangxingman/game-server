package com.game.core.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@Accessors
public class MessageType{

    /**命令*/
    private short cmd;

    /**基础类型*/
    private  AbsMessageType absMessageType;

}

package com.game.core.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther : wx
 * @Desc :   封装客户端的消息
 * @Date :  下午 5:38 2019/5/17 0017
 * @explain :
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NetMessage {


   private byte[] bytes;

   private  MessageType messageType;
}

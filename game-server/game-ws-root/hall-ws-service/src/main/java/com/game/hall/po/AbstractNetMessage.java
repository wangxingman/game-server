package com.game.hall.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther : wx
 * @Desc :   封装客户端的消息
 * @Date :  下午 5:38 2019/5/17 0017
 * @explain :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbstractNetMessage {
    
   private  MessageBody messageBody;

   private  MessageType messageType;
}

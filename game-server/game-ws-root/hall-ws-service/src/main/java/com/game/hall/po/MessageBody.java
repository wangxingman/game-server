package com.game.hall.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 5:37 2019/5/17 0017
 * @explain :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageBody {
    /**
     * 存储数据
     */
    private byte[] bytes;

}

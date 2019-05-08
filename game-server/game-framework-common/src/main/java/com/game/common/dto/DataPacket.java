package com.game.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 8:49 2019/5/5 0005
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPacket {
    private int cmd;
    private String data;
}

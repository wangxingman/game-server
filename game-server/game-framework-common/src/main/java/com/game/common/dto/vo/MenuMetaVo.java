package com.game.common.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:39 2019/6/24 0024
 * @explain :
 */
@Data
@AllArgsConstructor
public class MenuMetaVo implements Serializable {
    private String title;

    private String icon;
}

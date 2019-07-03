package com.game.common.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:34 2019/7/2 0002
 * @explain :
 */
@Data
public class DictDetailDto  implements Serializable {
    private Long id;

    /**
     * 字典标签
     */
    private String label;

    /**
     * 字典值
     */
    private String value;

    /**
     * 排序
     */
    private String sort;

    /**
     * 字典id
     */
    private String dictName;
}

package com.game.common.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:56 2019/6/28 0028
 * @explain :
 */
@Data
public class DeptSmallDto implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;
}

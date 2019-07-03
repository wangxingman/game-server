package com.game.common.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:25 2019/7/2 0002
 * @explain : 字典
 */
@Data
public class DictDto implements Serializable {

    private Long id;

    /**
     * 字典名称
     */
//    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    /**
     * 描述
     */
//    @Query(type = Query.Type.INNER_LIKE)
    private String remark;
}

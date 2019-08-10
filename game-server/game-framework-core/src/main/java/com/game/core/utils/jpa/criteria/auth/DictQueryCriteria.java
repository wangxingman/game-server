package com.game.core.utils.jpa.criteria.auth;

import com.game.core.annotation.Query;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:09 2019/7/11 0011
 * @explain :
 */
@Data
public class DictQueryCriteria implements Serializable {
    private Long id;

    /**
     * 字典名称
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    /**
     * 描述
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String remark;
}

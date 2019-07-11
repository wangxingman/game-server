package com.game.core.utils.jpa.criteria;

import com.game.core.annotation.Query;
import lombok.Data;

import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:31 2019/7/11 0011
 * @explain :
 */
@Data
public class DeptQueryCriteria {

    @Query(type = Query.Type.IN, propName = "id")
    private Set<Long> ids;

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query
    private Long pid;
}

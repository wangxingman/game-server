package com.game.core.utils.jpa.criteria.auth;

import com.game.core.annotation.Query;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:50 2019/7/11 0011
 * @explain :
 */
@Data
@NoArgsConstructor
public class JobQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query(propName = "id", joinName = "dept")
    private Long deptId;

    @Query(propName = "id", joinName = "dept", type = Query.Type.IN)
    private Set<Long> deptIds;
}

package com.game.core.utils.jpa.criteria;

import com.game.core.annotation.Query;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:31 2019/7/10 0010
 * @explain : 定义查询属性
 */
@Data
public class UserQueryCriteria implements Serializable {

    @Query
    private Long id;

    @Query(propName = "id", type = Query.Type.IN, joinName = "dept")
    private Set<Long> deptIds;

    @Query(type = Query.Type.INNER_LIKE)
    private String username;

    @Query(type = Query.Type.INNER_LIKE)
    private String email;

    @Query
    private Boolean enabled;

    private Long deptId;

}

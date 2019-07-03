package com.game.auth.search;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:53 2019/6/29 0029
 * @explain :
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

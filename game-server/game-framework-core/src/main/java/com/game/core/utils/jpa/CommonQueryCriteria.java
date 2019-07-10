package com.game.core.utils.jpa;

import com.game.core.annotation.Query;
import lombok.Data;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:26 2019/7/10 0010
 * @explain :  公共查询类
 */
@Data
public class CommonQueryCriteria {
    @Query(type = Query.Type.INNER_LIKE)
    private String name;
}

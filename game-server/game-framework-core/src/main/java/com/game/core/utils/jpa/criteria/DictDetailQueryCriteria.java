package com.game.core.utils.jpa.criteria;

import com.game.core.annotation.Query;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:13 2019/7/11 0011
 * @explain :
 */
@Data
public class DictDetailQueryCriteria implements Serializable {

    @Query(type = Query.Type.INNER_LIKE)
    private String label;

    @Query(propName = "name",joinName = "dict")
    private String dictName;
}

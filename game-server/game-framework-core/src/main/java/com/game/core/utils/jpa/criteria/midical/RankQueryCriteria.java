package com.game.core.utils.jpa.criteria.midical;

import com.game.core.annotation.Query;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:30 2019/8/8 0008
 * @explain :  排班的模糊查询数据
 */
@Data
public class RankQueryCriteria implements Serializable {

    private Long id;

    /**
     * 时间
     */
    @Query(type = Query.Type.INNER_LIKE)
    private Timestamp timestamp;

    /**
     * 科室名
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String officeName;

    /**
     * 科室Id
     */
    @Query(type = Query.Type.INNER_LIKE)
    private Long officeId;
    
}

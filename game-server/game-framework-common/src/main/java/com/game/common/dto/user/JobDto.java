package com.game.common.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:16 2019/6/28 0028
 * @explain :
 */
@Data
@NoArgsConstructor
public class JobDto implements Serializable {
    /**
     * ID
     */
    private Long id;

    private Long sort;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态
     */
    private Boolean enabled;

    private DeptDto dept;

    /**
     * 如果分公司存在相同部门，则显示上级部门名称
     */
    private String deptSuperiorName;

    /**
     * 创建日期
     */
    private Timestamp createTime;

    public JobDto(String name, Boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }
}

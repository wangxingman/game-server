package com.game.common.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:16 2019/6/24 0024
 * @explain :
 */
@Data
public class DeptDto  implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    @NotNull
    private Boolean enabled;

    /**
     * 上级部门
     */
    private Long pid;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptDto> children;

    private Date createTime;

    public String getLabel() {
        return name;
    }
}

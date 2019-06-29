package com.game.common.dto.user;

import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:15 2019/6/24 0024
 * @explain :
 */
@Data
public class RoleDto {

    private Long id;

    private String name;

    private String dataScope;

    private Integer level;

    private String remark;

    private Set<PermissionDto> permissions;

    private Set<MenuDto> menus;

    private Set<DeptDto> depts;

    private Date createTime;
    
}

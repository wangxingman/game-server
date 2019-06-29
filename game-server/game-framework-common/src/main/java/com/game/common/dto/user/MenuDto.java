package com.game.common.dto.user;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:18 2019/6/24 0024
 * @explain :
 */
@Data
public class MenuDto {

    private Long id;

    private String name;

    private Long sort;

    private String path;

    private String component;

    private Long pid;

    private Boolean iFrame;

    private String icon;

    private List<MenuDto> children;

    private Date createTime;
    
}

package com.game.common.dto.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:37 2019/6/24 0024
 * @explain :
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuVo {
    private String name;

    private String path;

    private String redirect;

    private String component;

    private Boolean alwaysShow;

    private MenuMetaVo meta;

    private List<MenuVo> children;
}

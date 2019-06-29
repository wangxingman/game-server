package com.game.common.dto.user;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:20 2019/6/24 0024
 * @explain :
 */
@Data
public class PermissionDto {
    private Long id;

    private String name;

    private Long pid;

    private String alias;

    private Date createTime;

    private List<PermissionDto> children;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", alias='" + alias + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

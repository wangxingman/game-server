package com.game.common.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:35 2019/6/20 0020
 * @explain : 按钮
 */
@Entity
@Table(name = "g_button")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Button implements Serializable {

    /**
     * 主键
     */
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 按钮id
     */
    private String buttonId;

    /**
     * 按钮名称
     */
    private String buttonName;

    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 权限id
     */
    private Long permissionId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否可授予
     */
    private Integer isGrant;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否选中
     */
    private boolean checked;

    /**
     * 关联按钮
     */
    private Permission permission;
}

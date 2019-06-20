package com.game.common.entity.user;
import lombok.*;

import javax.persistence.*;
import	java.io.Serializable;
import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:32 2019/6/20 0020
 * @explain :  菜单
 */
@Entity
@Table(name = "g_menu")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /**
     * 菜单名
     */
    private String menuName;
    /**
     * 菜单链接
     */
    private String menuLink;
    /**
     * 图标
     */
    private String menuIcon;
    /**
     * 父级ID
     */
    private Long parentId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 权限ID
     */
    private Long permissionId;
    /**
     * 是否可授予
     */
    private Integer isGrant;
    /**
     * 权限值
     */
    private Permission permission;
    /**
     * 子菜单
     */
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Menu> children;

    /**
     * 关联按钮
     */
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Button> buttons;

    /**
     * 是否选中
     */
    private boolean checked;

}

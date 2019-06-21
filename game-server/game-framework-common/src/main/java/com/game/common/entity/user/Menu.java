package com.game.common.entity.user;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import	java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:32 2019/6/20 0020
 * @explain :  菜单
 */
@Entity
@Table(name = "g_menu")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = {Update.class})
    private Long id;

    /**
     * 菜单名称
     */
    @NotBlank
    private String name;

    /**
     * 排序
     */
    @Column(unique = true)
    @NotNull
    private Long sort;

    /**
     * 路径
     */
    @Column(name = "path")
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     * 图标
     */
    private String icon;

    /**
     * 上级菜单ID
     */
    @Column(name = "pid",nullable = false)
    private Long pid;

    /**
     * 是否为外链 true/false
     */
    @Column(name = "i_frame")
    private Boolean iFrame;

    @ManyToMany(mappedBy = "menus")
    @JsonIgnore
    private Set<Role> roles;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    public interface Update{}
}

package com.game.common.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:57 2019/6/21 0021
 * @explain :   角色
 */
@Entity
@Table(name = "g_role")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = {Update.class})
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    /**
     * 数据权限类型 全部 、 本级 、 自定义
     */
    @Column(name = "data_scope")
    private String dataScope = "本级";

    /**
     * 数值越小，级别越大
     */
    @Column(name = "level")
    private Integer level = 3;

    @Column
    private String remark;

    /**
     * 角色 权限
     */
    @ManyToMany
    @JoinTable(name = "roles_permissions", joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "id")})
    private Set<Permission> permissions;

    /**
     * 角色 菜单
     */
    @ManyToMany
    @JoinTable(name = "roles_menus", joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "menu_id",referencedColumnName = "id")})
    private Set<Menu> menus;

    /**
     * 角色 部门
     */
    @ManyToMany
    @JoinTable(name = "roles_depts", joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "dept_id",referencedColumnName = "id")})
    private Set<Dept> depts;
    
    
    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;


    public interface Update{}
}

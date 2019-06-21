package com.game.common.entity.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.statement.update.Update;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:36 2019/6/20 0020
 * @explain :权限
 */
@Entity
@Table(name = "g_permission")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = {Update.class})
    private Long id;

    @NotBlank
    private String name;

    /**
     * 上级类目
     */
    @NotNull
    @Column(name = "pid",nullable = false)
    private Long pid;

    @NotBlank
    private String alias;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;

    @Column(name = "create_time")
    private Date createTime;
}

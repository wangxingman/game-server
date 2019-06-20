package com.game.common.entity.user;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

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
    
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /**
     * 权限名字
     */
    private String permissionName;

    /**
     * 权限类型
     */
    private Integer permissionType;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    @JSONField(serialize = false) 
    @Null
    private Date updateTime;
}

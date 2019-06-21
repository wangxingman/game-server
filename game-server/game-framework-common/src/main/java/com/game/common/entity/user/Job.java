package com.game.common.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:19 2019/6/21 0021
 * @explain :  职业
 */
@Entity
@Data
@Table(name="g_job")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = Update.class)
    private Long id;

    /**
     * 名称
     */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    @Column(unique = true)
    @NotNull
    private Long sort;

    /**
     * 状态
     */
    @Column(name = "enabled",nullable = false)
    @NotNull
    private Boolean enabled;

    /**
     * 职业 对应一个部门
     */
    @OneToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    public @interface Update {}

}

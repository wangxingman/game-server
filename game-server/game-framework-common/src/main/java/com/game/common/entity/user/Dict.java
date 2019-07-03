package com.game.common.entity.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: wx
 * @Date  : 下午 2:33 2019/7/2 0002 
 * @params: 
 * @Desc  :
 */
@Entity
@Data
@Table(name="dict")
public class Dict implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = Update.class)
    private Long id;

    /**
     * 字典名称
     */
    @Column(name = "name",nullable = false,unique = true)
    @NotBlank
    private String name;

    /**
     * 描述
     */
    @Column(name = "remark")
    private String remark;

    @OneToMany(mappedBy = "dict",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private List<DictDetail> dictDetails;

    public @interface Update {}
}
package com.game.see.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:31 2019/8/13 0013
 * @explain : 回收管理人
 */
@Data
@Builder
@Entity
@Table(name="medical_ware_house_user")
@org.hibernate.annotations.Table(appliesTo = "medical_ware_house_user",comment = "回收管理人")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalWareHouseUser implements Serializable {

    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = MedicalUser.Update.class)
    private Long id;

    /**
     *  用户的姓名
     */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /**
     *  回收人员类型
     */
    @Column(name = "type",nullable = false)
    @NotNull
    private Integer type;

    /**
     *  用户的性别
     */
    @Column(name = "six",nullable = false)
    @NotNull
    private Integer six;

    /**
     *  用户的年龄
     */
    @Column(name = "age",nullable = false)
    @NotNull
    private Integer age;

    /**
     *  用户的电话
     */
    @Column(name = "phone",nullable = false)
    @NotNull
    private Integer phone;

    /**
     *  用户的建档日期
     */
    @Column(name = "createTime",nullable = false)
    @NotNull
    private Timestamp createTime;

}

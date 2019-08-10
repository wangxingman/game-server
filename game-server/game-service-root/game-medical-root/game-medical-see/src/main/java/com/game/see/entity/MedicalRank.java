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
 * @Date :  上午 10:34 2019/7/24 0024
 * @explain : 排班 【时间预约】
 *            某个排班 下面 预约多个医生
 *            一个医生 对应 多个 用户 预约
 */
@Data
@Builder
@Entity
@Table(name="medical_rank")
@org.hibernate.annotations.Table(appliesTo = "medical_rank",comment = "排班表")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRank implements Serializable {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 序列号
     */
    @Column(name = "orderNumber",nullable = false)
    @NotBlank
    private String orderNumber;

    /**
     *  创建日期
     */
    @Column(name = "createTime",nullable = false)
    @NotNull
    private Timestamp createTime;

    /**
     *  修改日期
     */
    @Column(name = "updateTime",nullable = false)
    @NotNull
    private Timestamp updateTime;

    /**
     *  排班的具体哪一天
     */
    @Column(name = "schedulingTime",nullable = false)
    @NotNull
    private Timestamp schedulingTime;

    /**
     *  开始预约日期
     */
    @Column(name = "startTime",nullable = false)
    @NotNull
    private Timestamp startTime;
    

    /**
     *  科室名字
     */
    @Column(name = "officeName",nullable = false)
    @NotBlank
    private String officeName;

    /**
     *  科室Id
     */
    @Column(name = "officeId",nullable = false)
    @NotBlank
    private String officeId;
    

}

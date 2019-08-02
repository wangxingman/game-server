package com.game.see.entity.handle;

import com.alibaba.fastjson.annotation.JSONField;
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
 * @Date :  下午 5:22 2019/7/29 0029
 * @explain : 灭菌表
 */
@Data
@Builder
@Entity
@Table(name="medical_sterilization")
@org.hibernate.annotations.Table(appliesTo = "medical_sterilization",comment = "霉菌表")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalSterilization implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 灭菌流水编号
     */
    @Column(name = "sterilization_no",nullable = false)
    @NotBlank
    private String sterilizationNo;

    /**
     * 回收id
     */
    @Column(name = "medical_recycle_id",nullable = false)
    @NotNull
    private Integer medicalRecycleId;

    /**
     * 灭菌状态 0 未开始 1 进行中 2结束
     */
    @Column(name = "sterilization_status",nullable = false)
    @NotNull
    private Integer sterilizationStatus;

    /**
     * 灭菌数量
     */
    @Column(name = "sterilization_number",nullable = false)
    @NotNull
    private Integer sterilizationNumber;

    /**
     * 灭菌人id
     */
    @Column(name = "sterilization_user_id",nullable = false)
    @NotNull
    private Long sterilizationUserId;

    /**
     * 灭菌人编号
     */
    @Column(name = "sterilization_user_no",nullable = false)
    @NotBlank
    private String sterilizationUserNo;

    /**
     * 灭菌人姓名
     */
    @Column(name = "sterilization_user_name",nullable = false)
    @NotBlank
    private String sterilizationUserName;

    /**
     * 是否合格 1 是 0 否
     */
    @Column(name = "is_qualified",nullable = false)
    @NotNull
    private Integer isQualified;

    /**
     * 审核人id
     */
    @Column(name = "qualified_user_id",nullable = false)
    @NotNull
    private Long qualifiedUserId;

    /**
     * 审核人编号
     */
    @Column(name = "qualified_user_no",nullable = false)
    @NotBlank
    private String qualifiedUserNo;

    /**
     * 审核人姓名
     */
    @Column(name = "qualified_user_name",nullable = false)
    @NotBlank
    private String qualifiedUserName;

    /**
     * 数据记录时间
     */
    @Column(name = "recordTime",nullable = false)
    private Date recordTime;

    /**
     * 结束时间
     */
    @Column(name = "endTime",nullable = false)
    private Date endTime;

    /**
     * 是否入库 1是 0 否
     */
    @Column(name = "is_warehouse",nullable = false)
    @NotNull
    private Integer isWarehouse;

    /**
     * 创建时间
     */
    @Column(name = "createTime",nullable = false)
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "updateTime",nullable = false)
    private Date updateTime;
    
}

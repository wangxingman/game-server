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
 * @Date :  下午 4:04 2019/7/29 0029
 * @explain : 清洗
 */
@Data
@Builder
@Entity
@Table(name="medical_drug_clean")
@org.hibernate.annotations.Table(appliesTo = "medical_drug_clean",comment = "清洗表")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalDrugClean implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 清洗流水编号
     */
    @Column(name = "clean_no",nullable = false)
    @NotBlank
    private String cleanNo;

    /**
     * 回收id
     */
    @Column(name = "medical_recycle_id",nullable = false)
    @NotNull
    private Integer medicalRecycleId;
    
    /**
     * 录入时间
     */
    private Date recordTime;

    /**
     * 清洗数量
     */
    @Column(name = "clean_number",nullable = false)
    @NotNull
    private Integer cleanNumber;
    
    /**
     * 清洗状态 0 未开始 1 进行中 2结束
     */
    @Column(name = "clean_status",nullable = false)
    @NotNull
    private Integer cleanStatus;

    /**
     * 清洗人id
     */
    @Column(name = "clean_user_id",nullable = false)
    @NotNull
    private Long cleanUserId;

    /**
     * 清洗人姓名
     */
    @Column(name = "clean_user_name",nullable = false)
    @NotBlank
    private String cleanUserName;

    /**
     * 是否合格 0 未审核 1 通过 2不通过
     */
    @Column(name = "is_qualified",nullable = false)
    @NotNull
    private Integer isQualified;

    /**
     * 审核时间
     */
    @Column(name = "qualified_time",nullable = false)
    private Date qualifiedTime;

    /**
     * 审核人ID
     */
    @Column(name = "auditor_user_id",nullable = false)
    @NotNull
    private Long auditorUserId;

    /**
     * 审核姓名
     */
    @Column(name = "auditor_user_name",nullable = false)
    @NotBlank
    private String auditorUserName;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    @JSONField(serialize = false)
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time",nullable = false)
    @JSONField(serialize = false)
    private Date updateTime;
}

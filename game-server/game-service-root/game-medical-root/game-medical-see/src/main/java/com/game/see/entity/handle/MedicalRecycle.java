package com.game.see.entity.handle;

import com.alibaba.fastjson.annotation.JSONField;
import com.game.see.entity.MedicalDrug;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:37 2019/7/29 0029
 * @explain : 回收表
 */
@Data
@Builder
@Entity
@Table(name = "medical_recycle")
@org.hibernate.annotations.Table(appliesTo = "medical_recycle", comment = "回收表")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecycle implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 回收编号
     */
    @Column(name = "recovery_no",nullable = false)
    @NotBlank
    private String recoveryNo;

    /**
     * 回收时间
     */
    @JSONField(serialize = false)
    private Date recoveryTime;

    /**
     * 备注
     */
    @Column(name = "remarks",nullable = false)
    @NotBlank
    private String remarks;

    /**
     * 对应回收数量信息
     */
    @OneToOne
    @JoinColumn(name = "medical_recovery_defect_id")
    private MedicalRecoveryDefect medicalRecoveryDefect;

    /**
     * 回收人ID
     */
    @Column(name = "recovery_user_id",nullable = false)
    @NotBlank
    private Long recoveryUserId;

    /**
     * 回收的物品id
     */
    @Column(name = "medical_drug_id",nullable = false)
    @NotBlank
    private Integer medicalDrugId;

    /**
     * 回收的物品的名字
     */
    @Column(name = "medical_drug_name",nullable = false)
    @NotBlank
    private String medicalDrugName;

    /**
     * 创建时间
     */
    @JSONField(serialize = false)
    private Date createTime;

    /**
     * 修改时间
     */
    @JSONField(serialize = false)
    private Date updateTime;
}

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
 * @Date :  下午 5:15 2019/7/29 0029
 * @explain : 回收损坏
 */
@Data
@Builder
@Entity
@Table(name="medical_recycle_defect")
@org.hibernate.annotations.Table(appliesTo = "medical_recycle_defect",comment = "回收损坏")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecoveryDefect implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 流水编号
     */
    @Column(name = "recovery_defect_no",nullable = false)
    @NotBlank
    private String recoveryDefectNo;

    /**
     * 回收物品总数量
     */
    @Column(name = "drug_num",nullable = false)
    @NotNull
    private Integer drugNum;

    /**
     * 缺失数量
     */
    @Column(name = "defect_num",nullable = false)
    @NotNull
    private Integer defectNum;

    /**
     * 损坏数量
     */
    @Column(name = "damage_num",nullable = false)
    @NotNull
    private Integer damageNum;

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

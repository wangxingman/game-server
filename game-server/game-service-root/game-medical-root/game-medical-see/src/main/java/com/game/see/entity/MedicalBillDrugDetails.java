package com.game.see.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:49 2019/7/26 0026
 * @explain : 器械明细病单
 */
@Data
@Builder
@Entity
@Table(name="medicalBill_DrugDetails")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalBillDrugDetails implements Serializable {
    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 总次数
     */
    @Column(name = "drug_number",nullable = false)
    @NotNull
    private Integer drugNumber;

    /**
     * 频次
     */
    @Column(name = "drug_frequency",nullable = false)
    @NotBlank
    private String drugFrequency;

    /**
     * 已使用
     */
    @Column(name = "drug_use_number",nullable = false)
    @NotNull
    private Integer drugUserNumber;

    /**
     * 器械 id
     */
    @OneToOne
    @JoinColumn(name = "drug_id")
    private MedicalDrug medicalDrug;
}

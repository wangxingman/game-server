package com.game.see.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:49 2019/7/26 0026
 * @explain :药物明细病单 
 */
@Data
@Builder
@Entity
@Table(name="medicalBill_MedicineDetails")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalBillMedicineDetails {

    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 药物数量
     */
    @Column(name = "medicine_number",nullable = false)
    @NotNull
    private Integer medicineNumber;

    /**
     * 用法
     */
    @Column(name = "medicine_usage",nullable = false)
    @NotBlank
    private String medicineUsage;

    /**
     * 频次
     */
    @Column(name = "medicine_frequency",nullable = false)
    @NotBlank
    private String medicineFrequency;

    /**
     * 药物 id
     */
    @OneToOne
    @JoinColumn(name = "medicine_id")
    private MedicalMedicine medicalMedicine;

}

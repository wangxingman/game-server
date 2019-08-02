package com.game.see.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:30 2019/7/24 0024
 * @explain : 形成的所有病单
 */
@Data
@Builder
@Entity
@Table(name="medical_bill")
@org.hibernate.annotations.Table(appliesTo = "medical_bill",comment = "形成的所有病单")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalBill implements Serializable {

    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     *  流水的id
     */
    @Column(name = "bill_id",nullable = false)
    @NotBlank
    private String billId;

    /**
     *  诊断医生
     */
    @Column(name = "doctor_name",nullable = false)
    @NotBlank
    private String doctorName;

    /**
     *  就诊科室
     */
    @Column(name = "doctor_office",nullable = false)
    @NotBlank
    private String doctorOffice;

    /**
     *  诊断说明
     */
    @Column(name = "medical_bill_desc",nullable = false)
    @NotBlank
    private String medicalBillDesc;

    /**
     *  器械使用
     */
    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable(name="medicalBill_MedicalBillDrugDetails",joinColumns={@JoinColumn(name="b_id",referencedColumnName = "id")}
            ,inverseJoinColumns={@JoinColumn(name="bdd_id",referencedColumnName = "id")})
    private List<MedicalBillDrugDetails> medicalBillDrugDetails;

    /**
     *  药
     */
    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable(name="medicalBill_medicalBillMedicineDetails",joinColumns={@JoinColumn(name="b_id",referencedColumnName = "id")}
            ,inverseJoinColumns={@JoinColumn(name="bmd_id",referencedColumnName = "id")})
    private List<MedicalBillMedicineDetails> medicalBillMedicineDetails;
}

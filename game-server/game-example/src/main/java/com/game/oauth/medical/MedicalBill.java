package com.game.oauth.medical;

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
 * @Date :  上午 10:30 2019/7/24 0024
 * @explain : 形成的所有病单
 */
@Data
@Builder
@Entity
@Table(name="medical_bill")
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
     *  是否发药
     */
    @Column(name = "if_Medicine",nullable = false)
    @NotBlank
    private Integer ifMedicine;

    /**
     *  是否使用器械
     */
    @Column(name = "if_drug",nullable = false)
    @NotBlank
    private Integer ifDrug;

    /**
     * 患者id
     */
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private MedicalUser medicalUser;


}

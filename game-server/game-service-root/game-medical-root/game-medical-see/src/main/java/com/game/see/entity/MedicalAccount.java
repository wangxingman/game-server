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
 * @Date :  下午 6:54 2019/8/8 0008
 * @explain :  结算每月的记录
 */
@Data
@Builder
@Entity
@Table(name="medical_account")
@org.hibernate.annotations.Table(appliesTo = "medical_account",comment = "结算医生的记录")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalAccount implements Serializable {
    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     *  结算的no
     */
    @Column(name = "account_no",nullable = false)
    @NotBlank
    private String accountNo;

    /**
     *  医生name
     */
    @Column(name = "doctor_name",nullable = false)
    @NotBlank
    private String doctorName;

    /**
     *  当月值班次数
     */
    @Column(name = "duty_number",nullable = false)
    @NotNull
    private Integer dutyNumber;

    /**
     *  实发工资
     */
    @Column(name = "doctor_money",nullable = false)
    @NotNull
    private double doctorMoney;
    
}

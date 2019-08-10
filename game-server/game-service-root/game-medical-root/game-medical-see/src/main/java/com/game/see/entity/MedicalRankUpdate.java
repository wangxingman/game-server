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

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:21 2019/8/9 0009
 * @explain : 医生修改排班
 */
@Data
@Builder
@Entity
@Table(name="medical_rank_update")
@org.hibernate.annotations.Table(appliesTo = "medical_rank_update",comment = "医生修改排班")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRankUpdate implements Serializable {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     *  序列号
     */
    @Column(name = "orderNumber",nullable = false)
    @NotNull
    private String orderNumber;

    /**
     *  医生姓名
     */
    @Column(name = "medical_doctor_name",nullable = false)
    @NotBlank
    private String medicalDoctorName;

    /**
     *  医生id
     */
    @Column(name = "medical_doctor_id",nullable = false)
    @NotNull
    private Long medicalDoctorId;

    /**
     *  状态
     */
    @Column(name = "status",nullable = false)
    @NotNull
    private Integer status;

    /**
     *  换班MedicalRank
     */
    @Column(name = "medical_rank_number",nullable = false)
    @NotNull
    private String medicalRankNumber;

    /**
     *  用户的建档日期
     */
    @Column(name = "createTime",nullable = false)
    @NotNull
    private Timestamp createTime;

}

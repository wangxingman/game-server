package com.game.see.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:56 2019/7/24 0024
 * @explain : 医生信息
 */
@Data
@Builder
@Entity
@Table(name = "medical_doctor")
@org.hibernate.annotations.Table(appliesTo = "medical_doctor", comment = "医生信息表")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalDoctor implements Serializable {

    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 用户的id
     */
    @Column(name = "doctor_id", nullable = false)
    @NotNull
    private Long doctorId;

    /**
     * 用户的姓名
     */
    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    /**
     * 级别
     */
    @Column(name = "doctor_level", nullable = false)
    private Integer doctorLevel;

    /**
     * 用户的性别
     */
    @Column(name = "six", nullable = false)
    @NotNull
    private Integer six;

    /**
     * 用户的年龄
     */
    @Column(name = "age", nullable = false)
    @NotNull
    private Integer age;

    /**
     * 用户的电话
     */
    @Column(name = "phone", nullable = false)
    @NotNull
    private Integer phone;

    /**
     * 用户的建档日期
     */
    @Column(name = "createTime", nullable = false)
    @NotNull
    private Timestamp createTime;

    /**
     * 医生当月的上班次数
     */
    @Column(name = "schedulingNumberMonth", nullable = false)
    @NotNull
    private Integer schedulingNumberMonth;

    /**
     * 医生对应的科室
     */
    @OneToOne
    @JoinColumn(name = "office_id")
    private MedicalOffice medicalOffice;


}

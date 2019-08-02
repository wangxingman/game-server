package com.game.oauth.medical;

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
 * @Date :  上午 9:56 2019/7/24 0024
 * @explain : 医生信息
 */
@Data
@Builder
@Entity
@Table(name="medical_doctor")
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
     *  用户的id
     */
    @Column(name = "user_id",nullable = false)
    @NotBlank
    private String doctorId;

    /**
     *  用户的姓名
     */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /**
     *  用户的性别
     */
    @Column(name = "six",nullable = false)
    @NotBlank
    private Integer six;

    /**
     *  用户的年龄
     */
    @Column(name = "age",nullable = false)
    @NotBlank
    private Integer age;

    /**
     *  用户的电话
     */
    @Column(name = "phone",nullable = false)
    @NotBlank
    private Integer phone;

    /**
     *  用户的建档日期
     */
    @Column(name = "createTime",nullable = false)
    @NotBlank
    private Timestamp createTime;
                            
    /**
     *  医生对应的科室
     */
    @OneToOne
    @JoinColumn(name = "office_id")
    private MedicalOffice medicalOffice;

}

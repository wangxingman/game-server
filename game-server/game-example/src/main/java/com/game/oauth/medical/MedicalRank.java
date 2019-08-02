package com.game.oauth.medical;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import	java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:34 2019/7/24 0024
 * @explain : 排班 【时间预约】
 */
@Data
@Builder
@Entity
@Table(name="medical_rank")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRank implements Serializable {

    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = MedicalUser.Update.class)
    private Long id;

    /**
     *  排班日期
     */
    @Column(name = "createTime",nullable = false)
    @NotBlank
    private Timestamp createTime;

    /**
     *  修改日期
     */
    @Column(name = "updateTime",nullable = false)
    @NotBlank
    private Timestamp updateTime;

    /**
     *  排班的具体哪一天
     */
    @Column(name = "schedulingTime",nullable = false)
    @NotBlank
    private Timestamp schedulingTime;

    /**
     *  零时请假
     */
    @Column(name = "rank_leave",nullable = false)
    @NotBlank
    private Integer rankLeave;

    /**
     *  是否替换
     */
    @Column(name = "rank_replace",nullable = false)
    @NotBlank
    private Integer rankReplace;

    /**
     * 医生id
     */
    @OneToOne
    @JoinColumn(name = "doctorId")
    private MedicalDoctor medicalDoctor;

    /**
     *  科室
     */
    @OneToOne
    @JoinColumn(name = "officeId")
    private MedicalDoctor medicalOffice;

}

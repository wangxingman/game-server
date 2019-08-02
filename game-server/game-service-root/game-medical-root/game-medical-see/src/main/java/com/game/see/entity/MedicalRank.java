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
import java.util.List;
import java.util.Set;

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
@org.hibernate.annotations.Table(appliesTo = "medical_rank",comment = "排班表")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRank implements Serializable {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 序列号
     */
    @Column(name = "orderNumber",nullable = false)
    @NotNull
    private Long orderNumber;

    /**
     *  排班日期
     */
    @Column(name = "createTime",nullable = false)
    @NotNull
    private Timestamp createTime;

    /**
     *  修改日期
     */
    @Column(name = "updateTime",nullable = false)
    @NotNull
    private Timestamp updateTime;

    /**
     *  排班的具体哪一天
     */
    @Column(name = "schedulingTime",nullable = false)
    @NotNull
    private Timestamp schedulingTime;

    /**
     *  预约总数量
     */
    @Column(name = "all_number",nullable = false)
    @NotNull
    private Integer allNumber;

    /**
     *  以预约数量
     */
    @Column(name = "finish_number",nullable = false)
    @NotNull
    private Integer finishNumber;

    /**
     * 诊金
     */
    @Column(name = "money",nullable = false)
    @NotNull
    private Integer money;

    /**
     * 医生id
     */
    @OneToOne
    @JoinColumn(name = "doctorId")
    private MedicalDoctor medicalDoctor;

    /**
     *  排班下面的患者
     */
    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable(name="medical_rank_medical_subscribe",joinColumns={@JoinColumn(name="r_id",referencedColumnName = "id")}
            ,inverseJoinColumns={@JoinColumn(name="s_id",referencedColumnName = "id")})
    private List<MedicalSubscribe> medicalSubscribes;
}

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

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:57 2019/8/8 0008
 * @explain :预约 
 */
@Data
@Builder
@Entity
@Table(name="medical_rank_detail")
@org.hibernate.annotations.Table(appliesTo = "medical_rank_detail",comment = "排班表明细表")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRankDetail implements Serializable {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     *  MedicalRank_序列号
     */
    @Column(name = "rankOrderNumber",nullable = false)
    @NotNull
    private String rankOrderNumber;

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
     *  医生名字
     */
    @Column(name = "doctorName",nullable = false)
    @NotBlank
    private String doctorName;

    /**
     *  医生名字
     */
    @Column(name = "doctorId",nullable = false)
    @NotNull
    private Long doctorId;

    /**
     *  排班下面的患者
     */
    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable(name="medical_rank_detail_medical_subscribe",joinColumns={@JoinColumn(name="r_id",referencedColumnName = "id")}
            ,inverseJoinColumns={@JoinColumn(name="s_id",referencedColumnName = "id")})
    private List<MedicalSubscribe> medicalSubscribes;

}

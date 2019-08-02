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
 * @Date :  上午 10:28 2019/7/24 0024
 * @explain :  治疗项目
 */
@Data
@Builder
@Entity
@Table(name="medical_project")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalProject implements Serializable {

    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = MedicalUser.Update.class)
    private Long id;

    /**
     * 项目名称
     */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /**
     * 项目分类
     */
    @Column(name = "type",nullable = false)
    @NotBlank
    private String type;

    /**
     * 单位
     */
    @Column(name = "unit",nullable = false)
    @NotBlank
    private String unit;

    /**
     * 单价
     */
    @Column(name = "money",nullable = false)
    @NotBlank
    private double money;

    /**
     * 是否启用
     */
    @Column(name = "if_start",nullable = false)
    @NotBlank
    private double ifStart;

    /**
     * 对应的科室
     */
    @OneToOne
    @JoinColumn(name = "office_id")
    private MedicalOffice medicalOffice;
    
}

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
 * @Date :  下午 4:50 2019/7/24 0024
 * @explain : 药物 一次性用品
 */
@Data
@Builder
@Entity
@Table(name="medical_medicine")
@org.hibernate.annotations.Table(appliesTo = "medical_medicine",comment = "药物")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalMedicine implements Serializable {

    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     *  药平
     */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /**
     * 单位
     */
    @Column(name = "unit",nullable = false)
    @NotBlank
    private String unit;

    /**
     *  使用多少的数量
     */
    @Column(name = "need_number",nullable = false)
    @NotNull
    private Integer needNumber;

    /**
     * 甚于多少数量
     */
    @Column(name = "exceed_number",nullable = false)
    @NotNull
    private Integer exceedNumber;
    
    /**
     *  数量
     */
    @Column(name = "all_number",nullable = false)
    @NotNull
    private Integer allNumber;

}

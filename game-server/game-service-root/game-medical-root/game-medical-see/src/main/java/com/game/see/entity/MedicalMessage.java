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
 * @Date :  上午 10:40 2019/7/24 0024
 * @explain :  用户的测量信息
 */
@Data
@Builder
@Entity
@Table(name="medical_message")
@org.hibernate.annotations.Table(appliesTo = "medical_message",comment = "用户的测量信息表")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalMessage implements Serializable {

    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     *  身高
     */
    @Column(name = "height",nullable = false)
    @NotNull
    private double height;

    /**
     *  体重
     */
    @Column(name = "weight",nullable = false)
    @NotNull
    private double weight;

    /**
     *  体温
     */
    @Column(name = "heat",nullable = false)
    @NotNull
    private double heat;

}

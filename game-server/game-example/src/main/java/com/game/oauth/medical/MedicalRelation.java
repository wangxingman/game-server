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
 * @Date :  上午 9:49 2019/7/24 0024
 * @explain : 患者家属信息
 */
@Data
@Builder
@Entity
@Table(name="medical_relation")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRelation implements Serializable {

    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = MedicalUser.Update.class)
    private Long id;

    /**
     *  用户的姓名
     */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /**
     *  用户的电话
     */
    @Column(name = "phone",nullable = false)
    @NotBlank
    private Integer phone;


    /**
     *  用户的关系
     */
    @Column(name = "relation",nullable = false)
    @NotBlank
    private Integer relation;
    
}

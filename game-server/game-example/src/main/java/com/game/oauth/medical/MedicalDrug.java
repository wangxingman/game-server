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
 * @Date :  上午 10:30 2019/7/24 0024
 * @explain : 医疗物品【肯定是否有一次性物品】
 */
@Data
@Builder
@Entity
@Table(name="medical_drug")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalDrug implements Serializable {

    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = MedicalUser.Update.class)
    private Long id;

    /**
     *  药平
     */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /**
     *  是否一次性的物品
     */
    @Column(name = "once",nullable = false)
    @NotBlank
    private Integer once;

    /**
     *  数量
     */
    @Column(name = "number",nullable = false)
    @NotBlank
    private Integer number;

}

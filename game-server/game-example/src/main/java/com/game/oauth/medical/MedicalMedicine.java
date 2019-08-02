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
 * @Date :  下午 4:50 2019/7/24 0024
 * @explain : 药物 一次性用品
 */
@Data
@Builder
@Entity
@Table(name="medical_medicine")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalMedicine implements Serializable {

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
     *  数量
     */
    @Column(name = "number",nullable = false)
    @NotBlank
    private Integer number;

}

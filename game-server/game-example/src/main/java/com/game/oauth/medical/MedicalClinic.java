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
 * @Date :  上午 10:32 2019/7/24 0024
 * @explain :  所有的诊所
 */
@Data
@Builder
@Entity
@Table(name="medical_clinic")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalClinic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = MedicalUser.Update.class)
    private Long id;

    /**
     *  诊所的姓名
     */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /**
     *  诊所的地址
     */
    @Column(name = "address",nullable = false)
    @NotBlank
    private String address;
    
}

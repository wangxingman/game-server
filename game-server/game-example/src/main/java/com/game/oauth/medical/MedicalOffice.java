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
 * @Date :  上午 10:08 2019/7/24 0024
 * @explain :科室【医生所属科室】
 */
@Data
@Builder
@Entity
@Table(name="medical_office")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalOffice implements Serializable {

    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     *  科室名
     */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /**
     *  科室描述
     */
    @Column(name = "desc_message",nullable = false)
    @NotBlank
    private String descMessage;

}

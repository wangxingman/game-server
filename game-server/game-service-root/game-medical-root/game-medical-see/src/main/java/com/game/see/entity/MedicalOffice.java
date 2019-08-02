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
 * @Date :  上午 10:08 2019/7/24 0024
 * @explain :科室【医生所属科室】
 */
@Data
@Builder
@Entity
@Table(name="medical_office")
@org.hibernate.annotations.Table(appliesTo = "medical_office",comment = "科室表")
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

    /**
     *  创建时间
     */
    @Column(name = "create_time",nullable = false)
    @NotNull
    private Timestamp createTime;

    /**
     *  修改时间
     */
    @Column(name = "update_time",nullable = false)
    @NotNull
    private Timestamp updateTime;

}

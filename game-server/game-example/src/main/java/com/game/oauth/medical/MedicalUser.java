package com.game.oauth.medical;

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
import java.util.Set;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:45 2019/7/24 0024
 * @explain : 患者信息
 */
@Data
@Builder
@Entity
@Table(name="medical_user")
@org.hibernate.annotations.Table(appliesTo = "medical_user",comment = "患者表")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalUser implements Serializable {

    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = Update.class)
    private Long id;

    /**
     *  用户的id
     */
    @Column(name = "user_id",nullable = false)
    @NotBlank
    private String userId;

    /**
     *  用户的姓名
     */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /**
     *  用户的性别
     */
    @Column(name = "six",nullable = false)
    @NotBlank
    private Integer six;

    /**
     *  用户的年龄
     */
    @Column(name = "age",nullable = false)
    @NotBlank
    private Integer age;

    /**
     *  用户的电话
     */
    @Column(name = "phone",nullable = false)
    @NotBlank
    private Integer phone;

    /**
     *  用户的建档日期
     */
    @Column(name = "createTime",nullable = false)
    @NotBlank
    private Timestamp createTime;

    /**
     *  用户的就诊次数
     */
    @Column(name = "number",nullable = false, columnDefinition="int(11) COMMENT '用户的就诊次数'")
    @NotBlank
    private Integer number;

    /**
     *  用户是否会员
     */
    @Column(name = "member",nullable = false,columnDefinition="int(11) COMMENT '用户是否会员'")
    @NotBlank
    private Integer member;

    /**
     *  用户对应的药物过敏
     */
    @ManyToMany
    @JoinTable(name = "user_medicine",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "medicine_id",referencedColumnName = "id")})
    private Set<MedicalMedicine> medicalMedicines;
    /**
     *  用户对应的关系人物
     */
    @OneToOne
    @JoinColumn(name = "relation_id")
    private MedicalRelation medicalRelation;

    /**
     *  用户对应的信息
     */
    @OneToOne
    @JoinColumn(name = "messageId")
    private MedicalMessage medicalMessage;


    /**
     *  用户对应之前的病例
     */
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private List<MedicalCase> medicalCases;

    /**
     *  用户对应病单
     */
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private List<MedicalBill> medicalBills;


    public  @interface Update{
        
    }
}

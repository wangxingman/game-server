package com.game.see.entity;

import com.game.common.encode.MD5Util;
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
    @NotNull
    private Integer six;

    /**
     *  用户的年龄
     */
    @Column(name = "age",nullable = false)
    @NotNull
    private Integer age;

    /**
     *  用户的电话
     */
    @Column(name = "phone",nullable = false)
    @NotNull
    private Integer phone;

    /**
     *  用户的建档日期
     */
    @Column(name = "createTime",nullable = false)
    @NotNull
    private Timestamp createTime;

    /**
     *  用户的就诊次数
     */
    @Column(name = "number",nullable = false, columnDefinition="int(11) COMMENT '用户的就诊次数'")
    @NotNull
    private Integer number;

    /**
     *  用户是否会员
     */
    @Column(name = "member",nullable = false,columnDefinition="int(11) COMMENT '用户是否会员'")
    @NotNull
    private Integer member;

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
     *  用户对应病单
     */
    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable(name="medicalUser_medicalBill",joinColumns={@JoinColumn(name="u_id",referencedColumnName = "id")}
            ,inverseJoinColumns={@JoinColumn(name="b_id",referencedColumnName = "id")})
    private List<MedicalBill> medicalBills;

    /**
     *  用户对应的药物过敏
     */
    @ManyToMany
    @JoinTable(name = "user_medicine",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "medicine_id",referencedColumnName = "id")})
    private Set<MedicalMedicine> medicalMedicines;

    public  @interface Update{
        
    }

    public static void main(String[] args) {
        String encode = MD5Util.encode(String.valueOf(100));
        System.out.println(encode);
    }
}

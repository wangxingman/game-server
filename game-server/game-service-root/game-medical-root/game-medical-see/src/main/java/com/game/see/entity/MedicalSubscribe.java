package com.game.see.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import	java.io.Serializable;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 9:49 2019/7/26 0026
 * @explain : 是否看诊
 */
@Data
@Builder
@Entity
@Table(name="medical_subscribe")
@org.hibernate.annotations.Table(appliesTo = "medical_subscribe",comment = "是否看诊")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalSubscribe implements Serializable{

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     *  序列号
     */
    @Column(name = "subscribe_id",nullable = false)
    @NotNull
    private String subscribeId;

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
     * 是否支付
     */
    @Column(name = "if_money",nullable = false)
    @NotNull
    private Long ifMoney;

    /**
     * 是否看诊
     */
    @Column(name = "if_diagnosis",nullable = false)
    @NotNull
    private Long ifDiagnosis;

    /**
     * 看诊之后形成病单
     */
    @OneToOne
    @JoinColumn(name = "medical_bill_id")
    private MedicalBill medicalBill;
    
}

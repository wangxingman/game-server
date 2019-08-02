package com.game.see.entity.handle;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:23 2019/7/29 0029
 * @explain :  再次装配使用
 */
@Data
@Builder
@Entity
@Table(name="medical_again")
@org.hibernate.annotations.Table(appliesTo = "medical_again",comment = "再次装配使用表")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalAgain implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 组装编号
     */
    @Column(name = "again_no",nullable = false)
    @NotBlank
    private String againNo;

    /**
     * 回收id
     */
    @Column(name = "medical_recycle_id",nullable = false)
    @NotNull
    private Integer medicalRecycleId;

    /**
     * 是否组装
     */
    @Column(name = "is_again",nullable = false)
    @NotNull
    private Integer isAgain;

    /**
     * 组装数量
     */
    @Column(name = "again_number",nullable = false)
    @NotNull
    private Integer againNumber;

    /**
     * 组装人id
     */
    @Column(name = "again_user_id",nullable = false)
    @NotNull
    private Long againUserId;

    /**
     * 组装人编号
     */
    @Column(name = "again_user_no",nullable = false)
    @NotBlank
    private String againUserNo;

    /**
     * 组装人名称
     */
    @Column(name = "again_user_name",nullable = false)
    @NotBlank
    private String againUserName;

    /**
     * 是否合格 0 未审核 1 通过 2不通过
     */
    @Column(name = "is_qualified",nullable = false)
    @NotNull
    private Integer isQualified;
    
    /**
     * 审核人id
     */
    @Column(name = "qualified_user_id",nullable = false)
    @NotNull
    private Long qualifiedUserId;

    /**
     * 审核人编号
     */
    @Column(name = "qualified_user_no",nullable = false)
    @NotBlank
    private String qualifiedUserNo;

    /**
     * 创建时间
     */
    @Column(name = "createTime",nullable = false)
    @JSONField(serialize = false)
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "updateTime",nullable = false)
    @JSONField(serialize = false)
    private Date updateTime;

}

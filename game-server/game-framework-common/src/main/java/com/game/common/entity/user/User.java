package com.game.common.entity.user;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:52 2019/5/30 0030
 * @explain :用户
 */
@Entity
@Table(name = "g_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer uId;

    /**
     * 用户名【真实姓名】
     * @Length(min = 1, max = 20, message = "账户的长度只能在1-20之内", groups = {Insert.class, Login.class})
     */
    @Column(name = "u_name", nullable = true,columnDefinition="varchar(255) COMMENT '用户名【真实姓名】'")
    private String uName;


    /**
     * 用户身份证号【可做身份验证】
     * @Length(min = 17, max = 18, message = "用户身份证号只能在18", groups = {Insert.class, Login.class})
     */
    @Column(name = "u_number", nullable = true,columnDefinition="int(11) COMMENT '用户身份证号【可做身份验证】'")
    private Integer uNumber;

    /**
     * 用户邮箱
     */
    @Column(name = "u_email", nullable = true,columnDefinition="varchar(255) COMMENT '用户邮箱'")
    private String uEmail;


    /**
     * 用户电话
     * @Length(min = 10, max = 11, message = "角色名称的长度只能11之内", groups = {Insert.class})
     */
    @Column(name = "u_phone", nullable = true,columnDefinition="varchar(255) COMMENT '用户电话'")
    private String uPhone;

    /**
     * uToken
     * @Length(min = 10, max = 11, message = "角色名称的长度只能11之内", groups = {Insert.class})
     */
    @Column(name = "u_token", nullable = true,columnDefinition="varchar(255) COMMENT 'uToken'")
    private String uToken;


    /**
     * 用户游戏账号
     *  @Length(min = 5, max = 20, message = "用户游戏账号的长度只能5-20之内", groups = {Insert.class})
     */
    @Column(name = "u_account", nullable = true,columnDefinition="varchar(255) COMMENT '用户游戏账号'")
    private String uAccount;

    
    /**
     * 用户的密码
     *   @Pattern(regexp = "^.{6,20}|\\s*$", message = "密码的长度只能在6-20之内", groups = {})
     */
    @Column(name = "u_pass", nullable = true,columnDefinition="varchar(255) COMMENT '用户的密码'")
    private String uPass;

    /**
     * 创建时间
     */
    @JSONField(serialize = false)
    @Column(name = "createtime", nullable = true)
    private Date createtime;

    /**
     * 更新数据时间
     */
    @JSONField(serialize = false)
    @Column(name = "updatetime", nullable = true)
    private Date updatetime;
}

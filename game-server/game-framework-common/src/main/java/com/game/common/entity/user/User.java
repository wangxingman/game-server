package com.game.common.entity.user;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wx123
 * @since 2019-05-06
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("v_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "v_id", type = IdType.AUTO)
    private Integer vId;
    /**
     * 真实姓名
     */
    @TableField("v_name")
    private String vName;
    /**
     * 用户身份证号
     */
    @TableField("v_number")
    private Integer vNumber;
    /**
     * 用户邮箱
     */
    @TableField("v_email")
    private String vEmail;
    /**
     * 用户电话
     */
    @TableField("v_phone")
    private String vPhone;
    /**
     * 用户的唯一token
     */
    @TableField("v_token")
    private String vToken;
    /**
     * 用户游戏账号
     */
    @TableField("v_account")
    private String vAccount;
    /**
     * 用户的密码
     */
    @TableField("v_pass")
    private String vPass;

    @TableField("v_createtime")
    private Date vCreatetime;
    
    @TableField("v_updatetime")
    private Date vUpdatetime;

    @Override
    public String toString() {
        return "User{" +
        ", vId=" + vId +
        ", vName=" + vName +
        ", vNumber=" + vNumber +
        ", vEmail=" + vEmail +
        ", vPhone=" + vPhone +
        ", vToken=" + vToken +
        ", vAccount=" + vAccount +
        ", vPass=" + vPass +
        ", vCreatetime=" + vCreatetime +
        ", vUpdatetime=" + vUpdatetime +
        "}";
    }
}

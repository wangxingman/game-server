package com.game.hall.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:40 2019/6/5 0005
 * @explain :
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors
public class UserDto  {

    private Integer uId;

    private String uName;

    private Integer uNumber;

    private String uEmail;

    private String uPhone;

    private String uToken;

    private String uAccount;

    private String uPass;

    private Date createtime;

    private Date updatetime;
}

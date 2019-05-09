package com.game.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 8:35 2019/5/8 0008
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersistentRememberMeToken {
    private  String username;
    private  String series;
    private  String tokenValue;
    private  Date date;
}

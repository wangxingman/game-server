package com.game.login.properties;

import lombok.Data;

/**
 * @Author: wx
 * @Date  : 下午 8:50 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
@Data
public class OauthPageProperties {

    private  String  oauthLogin = "/oauthLogin";

    private  String oauthGrant = "/oauthGrant";
}

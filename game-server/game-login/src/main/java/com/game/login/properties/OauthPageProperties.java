package com.game.login.properties;

import lombok.Data;

@Data
public class OauthPageProperties {

    private  String  oauthLogin = "/oauthLogin";

    private String successExample = "/successExample";

    private String failExample = "/failExample";

    private  String oauthGrant = "/oauthGrant";
}

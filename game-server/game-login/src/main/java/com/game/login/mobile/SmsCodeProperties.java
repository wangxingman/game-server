package com.game.login.mobile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 60;//过期
    private String url = "/user1";
}

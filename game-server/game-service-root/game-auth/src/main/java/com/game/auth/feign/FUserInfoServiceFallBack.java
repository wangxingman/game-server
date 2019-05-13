package com.game.auth.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 2:56 2019/5/13 0013
 * @explain :
 */
@Slf4j
@Component
public class FUserInfoServiceFallBack implements FUserInfoService {

    @Override
    public String removeUserInfo() {
        log.error("feign调用出现异常");
        return "---------------------------------------";
    }
}

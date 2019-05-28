package com.game.login.feign.callback;

import com.game.login.feign.FHallService;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 8:31 2019/5/27 0027
 * @explain :
 */
@Slf4j
public class FHallServiceCallBack implements FHallService {

    @Override
    public boolean loginGateWay(Object item, String desc) {
        return false;
    }
}

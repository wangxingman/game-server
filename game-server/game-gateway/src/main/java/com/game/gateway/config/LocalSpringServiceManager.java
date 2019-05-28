package com.game.gateway.config;

import com.game.gateway.service.HallService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 8:20 2019/5/27 0027
 * @explain :  所有的注入spring容器的类
 */
@Slf4j
@Repository
@Data
public class LocalSpringServiceManager {
    
    static LocalSpringServiceManager localSpringServiceManager = new  LocalSpringServiceManager();

    public static LocalSpringServiceManager getInstance(){
        return localSpringServiceManager;
    }

    @Autowired
    private HallService hallService;
}

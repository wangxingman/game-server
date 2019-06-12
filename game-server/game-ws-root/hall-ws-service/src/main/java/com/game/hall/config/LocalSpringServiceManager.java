package com.game.hall.config;

import com.game.hall.service.GameService;
import com.game.hall.service.impl.GameServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 8:20 2019/5/27 0027
 * @explain :  所有的注入spring容器的类
 */
@Slf4j
@Data
@Component
public class LocalSpringServiceManager {
    
    static LocalSpringServiceManager localSpringServiceManager = new  LocalSpringServiceManager();

    public static LocalSpringServiceManager getInstance(){
        return localSpringServiceManager;
    }

    GameService gameService = new GameServiceImpl();
}

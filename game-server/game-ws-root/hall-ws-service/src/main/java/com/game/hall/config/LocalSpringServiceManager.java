package com.game.hall.config;

import com.game.hall.service.GameService;
import com.game.hall.service.impl.GameServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

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

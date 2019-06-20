package com.game.auth.MapperConfig;

import com.game.auth.mapper.MenuMapper;
import com.game.auth.mapper.UserMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:11 2019/6/20 0020
 * @explain :
 */
@Data
@Component
public class AuthMapperConfig {
    
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserMapper userMapper;
            

    /**
     * @Author: wx
     * @Date  : 上午 10:13 2019/6/20 0020 
     * @params: 
     * @Desc  :
     */
    public static AuthMapperConfig getInstance() {
      return new AuthMapperConfig();
    }

}

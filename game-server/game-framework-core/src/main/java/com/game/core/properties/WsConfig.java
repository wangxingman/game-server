package com.game.core.properties;

import com.game.common.Const.Const;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 2:11 2019/5/8 0008
 */
@Slf4j
public class WsConfig {

    public static Properties properties = new Properties();

    /**
     * @Author: wx
     * @Desc  : 初始化类型
     * @Date  : 下午 2:18 2019/5/8 0008 
     * @params:
     * @param prefixPath
     */
    public static void initWs(String prefixPath) {
        try {
            properties.load(new FileInputStream(new File(prefixPath)));
        } catch (IOException e) {
          log.error(" 初始化文件加载失败!");
            e.printStackTrace();
        }
    }

    /**
     * @Author: wx
     * @Desc  : 返回类型
     * @Date  : 下午 2:18 2019/5/8 0008 
     * @params: 
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

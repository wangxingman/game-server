package com.game.core.path;

/**
 * @Auther: wx
 * @Desc :
 * @Date : 下午 2:48 2019/5/8 0008
 */
public class ClassPathUtils{

    /**
     * @Author: wx
     * @Desc  :获取当前项目路径
     * @Date  : 下午 2:48 2019/5/8 0008
     * @params:
     */
    public static String  getClassPath(String configFileName) {
        return ClassPathUtils.class.getResource("/" + configFileName).getPath();
    }
}

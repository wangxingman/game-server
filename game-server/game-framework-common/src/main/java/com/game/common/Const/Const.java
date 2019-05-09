package com.game.common.Const;

/**
 * @Auther: wx
 * @Desc :  定义各种常量
 * @Date : 下午 2:32 2019/5/8 0008
 */
public interface Const {

    /**
     * @Author: wx
     * @Desc  : ws的各种常量
     * @Date  : 下午 2:33 2019/5/8 0008 
     * @params: 
     */
    interface ws {
        String PORT = "gamePort";
    }

    interface path {
        String PATH = "/conf/wsConfig.properties";
    }

    interface flag {
        boolean FALSE =  false;
        boolean TRUE =  true;
    }

    interface user {
        String USER_ID = "uid";
        String START_TIME = "startTime";
        String END_TIME = "end Time";
    }

    interface  date {
        /**半个小时*/
        int HALF_HOUR = 30 * 60 * 1000 ;
    }
    
}

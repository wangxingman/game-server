package com.game.common.constant;

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

    interface  login {
         String LOGIN_PAGE = "/authentication/require";

         String LOGIN_PROCESSING_URL = "/authentication/form";

         String AFTER_LOGING_PAGE = "/oauthLogin";
    }

    interface gateWay {
        String TOKEN = "token";
    }

    /**
     * @Author: wx
     * @Date  : 下午 5:52 2019/5/28 0028 
     * @params: 
     * @Desc  :
     */
    interface Attr {

        Integer YES = 1;

        Integer NO = 0;
    }

    /**
     * @Author: wx
     * @Date  : 下午 5:53 2019/5/28 0028
     * @params:
     * @Desc  : 服务地址
     */
    interface  Addr {
        String GATE_WAY = "127.0.0.1:8084";
    }

    /**
     * @Author: wx
     * @Date  : 下午 4:25 2019/5/17 0017
     * @params:
     * @Desc  :  大厅操作
     */
    interface hall{
        Integer   INIT_HALL = 000;
        Integer   JOIN_HALL = 123;
        Integer   JOIN_HALL_REP = 124;
        Integer   NAME_HALL = 111;
        Integer   NAME_HALL_REP = 222;
    }

    /**
     * @Author: wx
     * @Date  : 下午 4:32 2019/5/28 0028 
     * @params: 
     * @Desc  : 数值
     */
    interface number {
        int  ONE = 1;
        int  TWO = 2;
        int THREE = 3;
        int FOUR = 4;
        int FIVE = 5;
        int SIX = 6;
        int SENVEN = 7;
        int EGIHT = 8;
        int NINGHT = 9;
    }

}

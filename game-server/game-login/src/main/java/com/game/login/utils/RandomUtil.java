package com.game.login.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: wx
 * @Date  : 下午 8:39 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
public class RandomUtil {


    public static String randomString(int strLength) {
        Random rnd = ThreadLocalRandom.current();
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < strLength; i++) {
                ret.append(Integer.toString(rnd.nextInt(10)));
        }
        return ret.toString();
    }


}

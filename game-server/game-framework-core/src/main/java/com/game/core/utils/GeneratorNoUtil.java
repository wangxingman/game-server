package com.game.core.utils;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.text.RandomStringGenerator;

import java.util.Date;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:56 2019/7/29 0029
 * @explain :
 */
public class GeneratorNoUtil {

    /**
     * @Author: wx
     * @Date  : 下午 4:59 2019/7/29 0029
     * @params:
     * @Desc  : 随机生成器
     */
    private static final RandomStringGenerator GENERATOR = new RandomStringGenerator.Builder().withinRange('0', '9')
            .build();

    /**
     * @Author: wx
     * @Date  : 下午 4:59 2019/7/29 0029
     * @params:
     * @Desc  :生成编号算法
     */
    public static String GeneratorNo(String prefix) {
        String yyyyMMddHHmmss = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        String generate = GENERATOR.generate(4);
        return prefix + yyyyMMddHHmmss + generate;
    }
}

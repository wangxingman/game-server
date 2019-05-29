package com.game.core.example.cglib.Random;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 7:13 2019/5/29 0029
 * @explain :
 */
public class ExampleRandom {
    
    /**
     * 自动生成中文名字
     * @param len 名字的长度
     * @return
     */
    public static String getRandomJianTiZH(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            // 获取高位值
            hightPos = (176 + Math.abs(random.nextInt(39)));
            // 获取低位值
            lowPos = (161 + Math.abs(random.nextInt(93)));
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                // 转成中文
                str = new String(b, "GBK");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        return ret;
    }

    /**
     * 生成随机用户名，数字和字母组成
     * @param length 名字长度
     * @return
     */
    public static String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 生成指定范围里面的随机数
     * @param begin
     * @param end
     * @return
     */
    public static long randomNumber(long begin, long end){
        long rtn = begin + (long)(Math.random()*(end - begin));
        return rtn;
    }

    /**
     * 生成随机UUID
     * @return
     */
    public static String randomUUID(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }

    public static Date randomDate(String beginDate,String endDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);
            if (start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            return new Date(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Author: wx
     * @Date  : 下午 7:16 2019/5/29 0029
     * @params:
     * @Desc  : 生成 begin 到 end 之间的随机数
     */
    private static long random(long begin, long end){
        long rtn = begin + (long)(Math.random()*(end - begin));
        if (rtn == begin || rtn == end){
            return random(begin, end);
        }
        return rtn;
    }


    public static void main(String[] args) {
        String randomJianTiZH = getRandomJianTiZH(2);
        System.out.println(randomJianTiZH);
    }

}

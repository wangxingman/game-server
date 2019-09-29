package com.game.example.example;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:16 2019/8/30 0030
 * @explain :
 */
@Component
public class RedisExample {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void exampleOne() {
        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        String monthFormat = String.format("%1$02d", month+1);
        stringRedisTemplate.opsForValue().increment(monthFormat);
    }

    /**
     * @Author: wx
     * @Date  : 上午 10:24 2019/9/6 0006 
     * @params: 
     * @Desc  : 集合转数组
     */
    void foo() {
        List<Integer> strings = new ArrayList<>();
        strings.add(12312);
        strings.add(32131);
        Integer[] strings1 = new Integer[strings.size()];
        Integer[]  p = strings.toArray(strings1);
        for (Integer s : p) {
            System.out.println(s);
        }
    }

    /**
     * @Author: wx
     * @Date  : 下午 2:58 2019/9/18 0018 
     * @params: 
     * @Desc  :  测试点
     */
    void dian(Object... obj) {
        Integer o = null;
            for (int i = 0; i < obj.length; i++) {
                o  = (Integer)obj[2];
            }
        System.out.println(o);
    }
    
    /**
     * @Author: wx
     * @Date  : 下午 4:08 2019/9/20 0020 
     * @params: 
     * @Desc  :
     */
    void exampleFor() {
        for (int i = 0; i <3; i++) {
            System.out.println("--------");
               i = -1;
        }
    }


    public static void main(String[] args) {

        JSONObject json = new JSONObject();
        String data = "123123";
        json.put("data",data);
        JSONObject jsonObject = json.getJSONObject(data);
    }
}

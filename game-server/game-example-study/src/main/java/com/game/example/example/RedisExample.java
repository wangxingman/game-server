package com.game.example.example;

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

    public static void main(String[] args) {
       new RedisExample().foo();
    }
}

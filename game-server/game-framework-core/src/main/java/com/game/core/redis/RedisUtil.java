package com.game.core.redis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: wx
 * @Date  : 下午 5:46 2019/5/28 0028 
 * @params: 
 * @Desc  :  redis里面的操作
 */
@Component
public class RedisUtil {
    private static StringRedisTemplate stringRedisTemplate;

    private static RedisTemplate redisTemplate;

    @Autowired
    public RedisUtil(StringRedisTemplate stringRedisTemplate) {
        RedisUtil.stringRedisTemplate=stringRedisTemplate;
    }


    public static void save(Map<String,String > map){
        Set<String > keys =map.keySet();
        for(String key:keys){
            stringRedisTemplate.opsForValue().set(key,map.get(key));
        }
    }

    public static void saveFinish(String value){
        String value1=null;
        try {
            value1=stringRedisTemplate.opsForValue().get("FinishTask");
        }catch (Exception e){
        }
        if(value1==null){
            value1=value;
        }
        value1=value1+","+value;
        stringRedisTemplate.opsForValue().set("FinishTask",value1);
    }


    public static String getFinish(){
        try {
            return  stringRedisTemplate.opsForValue().get("FinishTask");
        }catch (Exception e){
            return "";
        }
    }


    public static void save(String key,String value,Long time){
        stringRedisTemplate.opsForValue().set(key, value,time,TimeUnit.HOURS);
    }


    public static void saveType(String key,String value,Long time,TimeUnit timeUnit){
        stringRedisTemplate.opsForValue().set(key, value,time,timeUnit);
    }


    public static void saveOfTime(Map<String,String> map,Long time){
        Set<String > keys =map.keySet();
        for(String key:keys){
            stringRedisTemplate.opsForValue().set(key,map.get(key),time,TimeUnit.HOURS);
        }
    }


    public static void del(String... names){
        Set<String> kSet = Stream.of(names).map(k -> k).collect(Collectors.toSet());
        stringRedisTemplate.delete(kSet);
    }

    //删除
    public static void delOne(String key){
        stringRedisTemplate.delete(key);
    }

    public static void saveObj(String key,Object obj){
        stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(obj));
    }

    //获取
    public static String  get(String key){
        try {
            return stringRedisTemplate.opsForValue().get(key);
        }catch (Exception e){
            return null;
        }
    }

    public static Object getObj(String key){
        try {
            return stringRedisTemplate.opsForValue().get(key);
        }catch (Exception e){
            return null;
        }
    }

    public static void saveHash(String key,String hashKey,Object value){
        stringRedisTemplate.opsForHash().put(key,hashKey, value);
    }

    public static void saveHashAll(String key,Map<?,?> map){
        stringRedisTemplate.opsForHash().putAll(key,map);
    }
    public static void delHash(String key,Object... hashKey){
        stringRedisTemplate.opsForHash().delete(key,hashKey);
    }
    public static void delHashAll(String key){
        stringRedisTemplate.opsForHash().delete(key);
    }
    public Object getHash(String key,String hashKey){
        try {
            return stringRedisTemplate.opsForHash().get(key, hashKey);
        } catch (Exception e) {
            return null;
        }
    }
    public static Object  getHashAll(String key){
        try {
            return stringRedisTemplate.opsForHash().keys(key);
        } catch (Exception e) {
            return null;
        }
    }

}

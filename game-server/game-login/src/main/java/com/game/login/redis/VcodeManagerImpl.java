package com.game.login.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: wx
 * @Date  : 下午 8:51 2019/7/3 0003 
 * @params: 
 * @Desc  :
 */
@Service
public class VcodeManagerImpl implements VcodeManager {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public String generateVcode() {
        return null;
    }

    @Override
    public void saveVcode(String mobile, String code, long expireTime, TimeUnit unit) {
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(mobile, code);
            redisTemplate.expire(mobile, expireTime, unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getVcode(String mobile) {
        Object result;
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        result = operations.get(mobile);
        return result;
    }

    @Override
    public void removeVcode(String mobile) {
        if(redisTemplate.hasKey(mobile)){
            redisTemplate.delete(mobile);
        }
    }
}

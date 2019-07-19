package com.game.core.log;

import com.game.core.annotation.AvoidRepeatableCommit;
import com.game.core.utils.web.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 7:24 2019/7/11 0011
 * @explain :
 */
@Slf4j
@Aspect
@Component
public class AvoidRepeatableCommitAspect {
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    /**
     * @Author: wx
     * @Date  : 下午 7:27 2019/7/11 0011 
     * @params: 
     * @Desc  : 切面方法
     */
    @Around("@annotation(com.game.core.annotation.AvoidRepeatableCommit)")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        HttpServletRequest request  = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = RequestUtil.getClientIp(request);
        //获取注解
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //目标类、方法
        String className = method.getDeclaringClass().getName();
        String name = method.getName();
        String ipKey = String.format("%s#%s",className,name);
        int hashCode = Math.abs(ipKey.hashCode());
        String key = String.format("%s_%d",ip,hashCode);
        log.info("ipKey={},hashCode={},key={}",ipKey,hashCode,key);
        AvoidRepeatableCommit avoidRepeatableCommit =  method.getAnnotation(AvoidRepeatableCommit.class);
        long timeout = avoidRepeatableCommit.timeout();
        if (timeout < 0){
            //过期时间5分钟
            timeout = 60*5;
        }
        String value = (String) redisTemplate.opsForValue().get(key);
        if (Objects.nonNull(value)){
            return "请勿重复提交";
        }
        redisTemplate.opsForValue().set(key, UUID.randomUUID(),timeout, TimeUnit.MILLISECONDS);
        //执行方法
        Object object = point.proceed();
        return object;
    }

}

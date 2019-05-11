package com.game.core.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * 类描述：
 * 创建人：@
 * 版本：version-01
 * DATA：2019/1/10
 * TIME：12:16
 * 注释：
 **/

@Aspect
@Slf4j
@Component
public class CastingAspect {

    @Autowired
    private static StringRedisTemplate stringRedisTemplate;

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void aspect() {

    }
    @Around("aspect()")
    public Object invoke(ProceedingJoinPoint pjp) throws  Throwable{
        Object proceed = null;
        try {
              proceed = pjp.proceed();
              return  proceed;
            }  finally {
            log(pjp, proceed);
        }
    }
    @Async
    public void log(ProceedingJoinPoint pjp,Object proceed) {
        MethodSignature es = (MethodSignature) pjp.getSignature();
        Method method = es.getMethod();
        Class returnType = es.getReturnType();
        CastingLog castingLog = method.getAnnotation(CastingLog.class);
        Boolean result = true;
        if(castingLog!=null) {
            Date date = new Date();
            // 获取当前的用户的信息【session获取】
            if(proceed == null){
                proceed = false;
            }// 返回类型 做失败 或者成功
            if(returnType.equals(Map.class)) {
                result = (Boolean) proceed;
            }
//            User user = (User)SessionUtil.get();
//            Object[] pjpArgs = pjp.getArgs();
//            ExpressionParser elParser = new SpelExpressionParser(); //spring的el表达式
//            log.debug("===============记录日志开始===============");
//            CastLog build = CastLog.builder().createDate(new Date())
//                    .type(castingLog.type().getKey())
//                    .judge(result ? mConst.Number.NUMBER_1:mConst.Number.NUMBER_0)
//                    .uName(user.getUAccount())
//                    .value(castingLog.value())
//                    .build();
//            String s = JSONObject.toJSONString(build);
//            stringRedisTemplate.opsForHash().put(mConst.CASTLOG, build.getUName(),s);
            log.debug("===============记录日志结束================");
        }
    }

    private String logForamt() {

        return  null;
    }
}

package com.game.core.log;

import com.game.common.constant.Const;
import com.game.common.entity.log.Log;
import com.game.common.mapper.LogMapper;
import com.game.core.mq.AbstractLogConvert;
import com.game.core.annotation.LogMessage;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  上午 11:10 2019/5/20 0020
 * @explain :
 */
@Slf4j
public class LogAspect {

    @Autowired
    private LogMapper logMapper;

    /**
     * 日志格式化
     */
    @Autowired
    private AbstractLogConvert logConvert;

    /**
     * 切入点
     */
    @Pointcut("within(@com.game.core.annotation *)")
    public void aspect() {
        
    }

    @Around("aspect()") //环绕通知
    public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
        Object proceed = null;
        try {
            //调用目标对象的方法
            proceed = pjp.proceed();
            return proceed;
        } finally {
            log(pjp, proceed);
        }
    }

    private void log(ProceedingJoinPoint pjp, Object proceed) {
        //获取接口的方法 
        MethodSignature es = (MethodSignature) pjp.getSignature();
        //获取方法
        Method method = es.getMethod();
        //获取到返回值类型
        Class rtype = es.getReturnType();
        Object[] args = pjp.getArgs();
        //方法注解参数
        LogMessage logMessage = method.getAnnotation(LogMessage.class);

        //TODO 可以发送消息
        if (method.isAnnotationPresent(LogMessage.class)) {
 /*           logConvert.sendMessage(logMessage, args, null);*/
        }
        if(logMessage!=null) {
            Boolean result = true;
            //执行是否成功
            if(proceed == null) {
                proceed = false;
            }
            if(rtype.equals(boolean.class) || rtype.equals(Boolean.class)) {
                result = (Boolean) proceed;
            }
            //spring的el表达式
            ExpressionParser elParser = new SpelExpressionParser();
            log.debug("===============记录日志开始===============");
            //todo 具体添加参数
            log(Log.builder()
                    .createtime(new Date())
                    .tType(logMessage.type().getKey())
                    .tUserId(null)
                    .tUserName(null)
                    .tOrOperation(result ? Const.Attr.YES : Const.Attr.NO)
                    .tOperationDetail(logMessage.value()).build());
            
            log.debug("===============记录日志结束================");
        }
    }

    /**
     * 添加日志
     */
    public void log(Log log) {
        logMapper.save(log);
    }
}

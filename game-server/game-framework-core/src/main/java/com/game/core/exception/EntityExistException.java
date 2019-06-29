package com.game.core.exception;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:30 2019/6/28 0028
 * @explain : 实体已存在的异常
 */
public class EntityExistException extends RuntimeException {
    public EntityExistException(Class clazz, Object... saveBodyParamsMap) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, saveBodyParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> saveBodyParams) {
        return StringUtils.capitalize(entity) +
                " 已存在 " +
                saveBodyParams;
    }

    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        return CommonException.getKvMap(keyType, valueType, entries);
    }


}

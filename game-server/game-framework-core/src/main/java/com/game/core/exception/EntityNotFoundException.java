package com.game.core.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import org.springframework.util.StringUtils;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:27 2019/6/28 0028
 * @explain :实体不存在的异常
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, Object... searchParamsMap) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) +
                " 不存在 " +
                searchParams;
    }

    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        return CommonException.getKvMap(keyType, valueType, entries);
    }
}

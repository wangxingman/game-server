package com.game.core.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 11:34 2019/6/28 0028
 * @explain :
 */
public class CommonException {

    static <K, V> Map<K, V> getKvMap(Class<K> keyType, Class<V> valueType, Object[] entries) {
        if (entries.length % 2 == 1) {
            throw new IllegalArgumentException("Invalid entries");
        }
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }
}

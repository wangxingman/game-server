package com.game.core.utils.jpa;

import cn.hutool.core.util.PageUtil;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:36 2019/7/10 0010
 * @explain : 分页工具
 */
public class JpaPageUtil extends PageUtil {

    /**
     * @Author: wx
     * @Date : 下午 2:38 2019/7/10 0010
     * @params:
     * @Desc :List 分页
     */
    public static List toPage(int page, int size, List list) {
        int fromIndex = page * size;
        int toIndex = page * size + size;

        if (fromIndex > list.size()) {
            return new ArrayList();
        } else if (toIndex >= list.size()) {
            return list.subList(fromIndex, list.size());
        } else {
            return list.subList(fromIndex, toIndex);
        }
    }

    /**
     * @Author: wx
     * @Date : 下午 2:38 2019/7/10 0010
     * @params:
     * @Desc :  Page 数据处理，预防redis反序列化报错
     */
    public static Map toPage(Page page) {
        Map map = new HashMap();

        map.put("content", page.getContent());
        map.put("totalElements", page.getTotalElements());

        return map;
    }

    /**
     * @Author: wx
     * @Date : 下午 2:38 2019/7/10 0010
     * @params:
     * @Desc :  Page 数据处理，预防redis反序列化报错
     */
    public static Map toPage(Object object, Object totalElements) {
        Map map = new HashMap();

        map.put("content", object);
        map.put("totalElements", totalElements);

        return map;
    }
}

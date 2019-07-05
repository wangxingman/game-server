package com.game.hall.config.name;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 7:37 2019/5/29 0029
 * @explain : 用户所有名字的操作
 */
@Slf4j
public class NameServer extends AbsServer implements InitName<String> {

    @Override
    public Set<String> initName( ) {
        try {
            //todo 这里查询应该单个
//          concurrentHashMap =
//                    .stream().collect(Collectors.toConcurrentMap(a -> a.getId(), a -> a.getUAccount()));
            if (Objects.isNull(linkedHash)) {
                log.info("当前的还没有用户!");
            }
        } catch (Exception e) {
            log.info("初始化名字失败！");
            e.printStackTrace();
        }
        return linkedHash;
    }

    @Override
    public boolean getName(String name) {
        boolean b = linkedHash.contains(name);
        return b;
    }
}
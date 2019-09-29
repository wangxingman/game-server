package com.game.example.storm.redis;

import org.apache.storm.redis.common.mapper.RedisDataTypeDescription;
import org.apache.storm.redis.common.mapper.RedisStoreMapper;
import org.apache.storm.tuple.ITuple;

/**
 * @Author : wx
 * @Desc :   定义 tuple 与 Redis 中数据的映射关系
 * @Date :  下午 4:26 2019/9/29 0029
 * @explain :
 */
public class WordCountStoreMapper implements RedisStoreMapper {

    private RedisDataTypeDescription description;
    private final String hashKey = "wordCount";

    public WordCountStoreMapper() {
        description = new RedisDataTypeDescription(RedisDataTypeDescription.RedisDataType.HASH,hashKey);
    }

    @Override
    public RedisDataTypeDescription getDataTypeDescription() {
        return description;
    }

    @Override
    public String getKeyFromTuple(ITuple iTuple) {
        return iTuple.getStringByField("word");
    }

    @Override
    public String getValueFromTuple(ITuple iTuple) {
        return iTuple.getStringByField("count");
    }
}

package com.game.example.storm;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 12:08 2019/9/29 0029
 * @explain :
 */
public class CountBolt  extends BaseRichBolt {

    private Map<String, Integer> counts = new HashMap<>();

    /**
     *来源于 IBolt，可以通过此方法获取用来发送 tuples 的 OutputCollector；
     **/
    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        
    }

    /**
     *来源于 IBolt，处理 tuples 和发送处理完成的 tuples；
     **/
    @Override
    public void execute(Tuple input) {
        String word = input.getStringByField("word");
        Integer count = counts.get(word);
        if (count == null) {
             count = 0;
        }
        count++;
        counts.put(word,count);
        // 输出
        System.out.print("当前实时统计结果:");
        counts.forEach((key,value) -> System.out.print(key + ":" + value + "; "));
        System.out.println();
    }

    /**
     *来源于 IComponent，声明发送的 tuples 的名称，这样下一个组件才能知道如何接收
     **/
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}

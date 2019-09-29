package com.game.example.storm.redis;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * @Author : wx
 * @Desc :    将每行数据按照指定分隔符进行拆分
 * @Date :  下午 4:18 2019/9/29 0029
 * @explain :
 */
public class SplitBolt extends BaseRichBolt {

    private OutputCollector outputCollector;
    
    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.outputCollector = collector;
    }

    @Override
    public void execute(Tuple input) {
        String line = input.getStringByField("line");
        String[] words = line.split("\t");
        for (String word : words) {
            outputCollector.emit(new Values(word, String.valueOf(1)));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word", "count"));
    }
}

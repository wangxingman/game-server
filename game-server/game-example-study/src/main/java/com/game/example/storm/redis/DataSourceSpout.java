package com.game.example.storm.redis;

import org.apache.commons.lang3.StringUtils;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.*;

/**
 * @Author : wx
 * @Desc :  产生词频样本的数据源
 * @Date :  下午 4:14 2019/9/29 0029
 * @explain :
 */
public class DataSourceSpout extends BaseRichSpout {

    private List<String> list = Arrays.asList("Spark", "Hadoop", "HBase", "Storm", "Flink", "Hive");

    private SpoutOutputCollector spoutOutputCollector;

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
       this.spoutOutputCollector = collector;
    }

    @Override
    public void nextTuple() {
       //模拟产生数据
        String lineData = productData();
        spoutOutputCollector.emit(new Values(lineData));
        Utils.sleep(1000);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("line"));
    }


    /**
     * 模拟数据
     */
    private String productData() {
        Collections.shuffle(list);
        Random random = new Random();
        int endIndex = random.nextInt(list.size()) % (list.size()) + 1;
        return StringUtils.join(list.toArray(), "\t", 0, endIndex);
    }

}

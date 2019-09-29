package com.game.example.storm;

import org.apache.commons.lang.StringUtils;
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
 * @Desc :
 * @Date :  上午 11:49 2019/9/29 0029
 * @explain :
 */
public class DataSourceSpout extends BaseRichSpout {

    private List<String> list = Arrays.asList("Spark", "Hadoop", "HBase", "Storm", "Flink", "Hive");

    private SpoutOutputCollector spoutOutputCollector;

    /**
     * 来源于 ISpout，可以通过此方法获取用来发送 tuples 的 SpoutOutputCollector；
     **/
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.spoutOutputCollector = spoutOutputCollector;
    }

    /**
     * 来源于 ISpout，必须在此方法内部发送 tuples；
     **/
    @Override
    public void nextTuple() {
       //模拟产生数据
        String lineData = productData();
        spoutOutputCollector.emit((new Values(lineData)));
        Utils.sleep(1000);
    }

    /**
     * 来源于 IComponent，声明发送的 tuples 的名称，这样下一个组件才能知道如何接受。
     **/
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("line"));
    }

    private String productData() {
        Collections.shuffle(list);
        Random random = new Random();
        int endIndex  = random.nextInt(list.size()) % (list.size()) + 1;
        Object[] array = list.toArray();
        return StringUtils.join(array,"\t", 0, endIndex);
    }
}

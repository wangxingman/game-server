package com.game.example.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:03 2019/9/29 0029
 * @explain :
 */
public class LocalWordCountApp {

    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("DataSourceSpout", new DataSourceSpout());

        // 指明将 DataSourceSpout 的数据发送到 SplitBolt 中处理
        builder.setBolt("SplitBolt",new SplitBolt()).shuffleGrouping("DataSourceSpout");

        //  指明将 SplitBolt 的数据发送到 CountBolt 中 处理
        builder.setBolt("CountBolt",new CountBolt()).shuffleGrouping("SplitBolt");

        // 创建本地集群用于测试 这种模式不需要本机安装 storm,直接运行该 Main 方法即可
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("LocalWordCountApp",new Config(),builder.createTopology());
    }
}

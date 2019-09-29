package com.game.example.hadoop.mapreduce.friendone;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 12:02 2019/9/16 0016
 * @explain : mapReduce 的 map阶段
 */
public class FirstFirendMapper extends Mapper<LongWritable, Text, Text, Text> {

    Text k = new Text();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //截断
        String[] split = value.toString().split(":");
        String user = split[0];
        String[] friends = split[1].split(",");
        for (String friend : friends) {
            k.set(friend);
            v.set(user);
            context.write(k, v);
        }
    }
}

package com.game.example.hadoop.mapreduce.jobs;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:08 2019/9/16 0016
 * @explain :
 */
public class TwoJobsMapper extends Mapper<LongWritable, Text,Text,Text> {

    Text k = new Text();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split("\t");
        String[] split = splits[0].split("--");
        k.set(split[0]);
        v.set(split[1]+"-->"+splits[1]);
        context.write(k,v);
    }
}

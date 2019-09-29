package com.game.example.hadoop.mapreduce.jobs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:02 2019/9/16 0016
 * @explain :
 */
public class JobsReducer extends Reducer<Text, IntWritable,Text,IntWritable> {

    IntWritable v = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {
            System.out.println(value.get());
            sum += value.get();
        }
        v.set(sum);
        context.write(key,v);
    }
    
}

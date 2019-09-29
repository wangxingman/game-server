package com.game.example.hadoop.mapreduce.jobs;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:12 2019/9/16 0016
 * @explain :
 */
public class TwoJobsReducer extends Reducer<Text,Text,Text,Text> {

    Text v = new Text();
    
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuffer result = new StringBuffer();
        for (Text value : values) {
            result.append(value.toString()+" ");
        }
        v.set(String.valueOf(result));
        context.write(key,v);
    }
}

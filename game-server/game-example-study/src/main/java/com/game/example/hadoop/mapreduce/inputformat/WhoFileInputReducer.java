package com.game.example.hadoop.mapreduce.inputformat;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:54 2019/9/16 0016
 * @explain :
 */
public class WhoFileInputReducer extends Reducer<Text, BytesWritable, Text, BytesWritable> {

    @Override
    protected void reduce(Text key, Iterable<BytesWritable> values, Context context) throws IOException, InterruptedException {
        //循环写出
        for (BytesWritable value : values) {
            context.write(key,value);
        }
    }
}

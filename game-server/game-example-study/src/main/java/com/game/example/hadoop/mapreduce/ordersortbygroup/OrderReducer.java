package com.game.example.hadoop.mapreduce.ordersortbygroup;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 6:09 2019/9/16 0016
 * @explain :
 */
public class OrderReducer extends Reducer<Order, NullWritable, Order, NullWritable> {

    @Override
    protected void reduce(Order key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(key, NullWritable.get());
    }
}

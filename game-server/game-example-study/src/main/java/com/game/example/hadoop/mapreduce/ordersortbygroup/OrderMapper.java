package com.game.example.hadoop.mapreduce.ordersortbygroup;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 6:01 2019/9/16 0016
 * @explain :
 */
public class OrderMapper extends Mapper<LongWritable, Text,Order, NullWritable> {


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Order k = new Order();
        String[] price = value.toString().split("\t");
        k.setId(Integer.parseInt(price[0]));
        k.setPrice(Integer.parseInt(price[2]));
        context.write(k,NullWritable.get());
        
    }
}

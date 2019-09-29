package com.game.example.hadoop.mapreduce.friendtwo;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:31 2019/9/16 0016
 * @explain :
 */
public class TwoFirendMapper extends Mapper<LongWritable, Text,Text,Text> {

    Text k = new Text();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        String friend = split[0];
        v.set(friend);
        String[] user = split[1].split(",");
        //排序
        Arrays.sort(user);
        for (int i = 0; i < user.length; i++) {
            for (int j=i+1;j<user.length;j++){
                k.set(user[i].toString()+"-"+user[j].toString());
                context.write(k,v);
            }
        }
    }
}

package com.game.example.hadoop.mapreduce.friendone;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 12:07 2019/9/16 0016
 * @explain :  reduce 阶段
 */
public class FirstFriendReducer extends Reducer<Text, Text, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
      StringBuffer sb = new StringBuffer();
        for (Text value : values) {
            sb.append(value+",");
        }
        //重新分组
        context.write(key,new Text(sb.toString()));
    }
}

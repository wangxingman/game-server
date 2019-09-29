package com.game.example.hadoop.mapreduce.inputformat;

import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:20 2019/9/16 0016
 * @explain :   该阶段主要将解析出的key/value交给用户编写的map()函数处理，并产生一系列的key/value
 */
public class WhoFileInputMapper extends Mapper<Text, ByteWritable,Text,ByteWritable> {

    @Override
    protected void map(Text key, ByteWritable value, Context context) throws IOException, InterruptedException {
        context.write(key,value);
    }
}

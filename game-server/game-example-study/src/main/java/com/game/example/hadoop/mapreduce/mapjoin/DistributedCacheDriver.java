package com.game.example.hadoop.mapreduce.mapjoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 5:52 2019/9/16 0016
 * @explain :
 */
public class DistributedCacheDriver {

    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InterruptedException {
        BasicConfigurator.configure();
        args = new String[]{"F:\\mapreduce\\mapjoin\\input","F:\\mapreduce\\mapjoin\\output"};
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(DistributedCacheDriver.class);

        job.setMapperClass(DistributedCacheMapper.class);

        job.setOutputKeyClass(TableBean.class);
        job.setOutputValueClass(NullWritable.class);

        //加入缓存数据
        job.addCacheFile(new URI("file:///F:/mapreduce/mapjoin/pd.txt"));

        job.setNumReduceTasks(0);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

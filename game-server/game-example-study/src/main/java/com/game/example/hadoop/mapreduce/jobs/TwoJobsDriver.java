package com.game.example.hadoop.mapreduce.jobs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 4:21 2019/9/16 0016
 * @explain :
 */
public class TwoJobsDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{"F:\\mapreduce\\jobs\\output","F:\\mapreduce\\jobs\\output1"};
        BasicConfigurator.configure();
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(TwoJobsDriver.class);
        job.setMapperClass(TwoJobsMapper.class);
        //用Combiner减少网络传输IO 提高mapreduce速度
          /* job.setReducerClass(JobsReducer.class);*/
        job.setCombinerClass(TwoJobsReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        //todo 
        //设置导出
        job.setInputFormatClass(KeyValueTextInputFormat.class);

        //todo 
        //数据清洗不需要Reduce阶段，设置reduceTask数量为0
        job.setNumReduceTasks(0);
        
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        System.exit(job.waitForCompletion(true)?0:1);
    }
}

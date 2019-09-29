package com.game.example.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:04 2019/9/16 0016
 * @explain :
 */
public class HDFSClient {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.default.name","hdfs://master:9000");
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop1:9000"), conf, "wangzili");

        fs.mkdirs(new Path("/wordcount/input"));

        //删除文件夹
//        fs.delete(new Path("/test/wangzili"),true);
        //3.关闭连接
        fs.close();
        System.out.println("运行结束");
    }
}

package com.game.example.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:04 2019/9/16 0016
 * @explain :
 */
public class HDFSApi {

    private static Configuration conf;
    private static FileSystem fs;

    public void HDFSClientStart() throws URISyntaxException, IOException, InterruptedException {
        conf = new Configuration();
        fs = FileSystem.get(new URI("hdfs://jerry:9000"), conf, "wangzili");
    }

    public void HDFSClientClose() throws IOException {
        fs.close();
    }

    /**
     * 文件上传
     */
    public void CopyFromLocalFile() throws IOException {
        fs.copyFromLocalFile(new Path("file:///home/wangzili/Pictures/鬼刀180824/pic1.jpg"), new Path("/test/pic"));
    }

    /**
     * 文件删除
     */
    public void deleteFile() throws IOException {
        fs.delete(new Path("/test/10000_access.log"), true);
    }

    /**
     * 文件下载
     */
    public void CopyToLocalFile() throws IOException {
        //delSrc是指是否将原文件删除
        //useRawLocalFileSystem 是否开启文件效验
        fs.copyToLocalFile(false, new Path("/test/pic"), new Path("/home/wangzili/mydata"), true);
    }

    /**
     * 修改文件名
     */
    public void UpdateFileName() throws IOException {
        fs.rename(new Path("/test/wangzili"), new Path("/test/wzl"));
    }

    /**
     * 查看文件详情
     */
    public void fileDetail() throws IOException {
        RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path("/test/pic"), true);
        while (files.hasNext()) {
            LocatedFileStatus fileStatus = files.next();
            //            文件名称，权限，长度，块信息
            System.out.println("文件名称:" + fileStatus.getPath().getName());
            System.out.println("权限:" + fileStatus.getOwner());
            System.out.println("权限信息:" + fileStatus.getPermission());
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println("副本保存在：" + host);
                }
            }
        }
    }

    /**
     * 判断文件还是文件夹
     */
    public void isDirectory() throws IOException {
        boolean directory = fs.isDirectory(new Path("/test/wzl"));
        boolean file = fs.isFile(new Path("/test/wzl"));
    }

    /**
     * 下载大文件的第一块
     */
    public void readFileSeek1() throws IOException {
        FSDataInputStream fis = fs.open(new Path("/test/hadoop-2.6.0-cdh5.7.0.tar.gz"));
        //获取输出流
        FileOutputStream fos = new FileOutputStream(new File("D://hadoop-2.6.0-cdh5.7.0.tar.gz.part0"));
        //流的对接（只拷贝128M)
        byte[] bytes = new byte[1024];
        for (int i = 0; i < 1024 * 128; i++) {
            fis.read(bytes);
            fos.write(bytes);
        }
        //关闭资源
        IOUtils.closeStream(fis);
        IOUtils.closeStream(fos);
    }

    /**
     * 下载文件第二个块
     */
    public void readFileSeek2() throws IOException {
        //获取输入流
        FSDataInputStream fis = fs.open(new Path("/test/hadoop-2.6.0-cdh5.7.0.tar.gz"));
        //设置指定起点
        fis.seek(1024 * 1024 * 128);
        //获取输出流
        FileOutputStream fos = new FileOutputStream(new File("D://hadoop-2.6.0-cdh5.7.0.tar.gz.part1"));

        IOUtils.copyBytes(fis, fos, conf);
        //关闭资源
        IOUtils.closeStream(fis);
        IOUtils.closeStream(fos);
    }
}

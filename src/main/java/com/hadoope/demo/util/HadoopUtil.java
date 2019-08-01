package com.hadoope.demo.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URI;

@Component
public class HadoopUtil {
    @Value("${hdfs_path}")
    private String hdfs_path;
    @Value("${hdfs_name}")
    private String hdfs_name;

    private static String hdfsPath;
    private static String hdfsName;

    /**
     * 获取HDFS配置信息
     * @return
     */
    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", hdfsPath);
        return configuration;
    }
    /**
     * 获取HDFS文件系统对象
     * @return
     * @throws Exception
     */
    public static FileSystem getFileSystem() throws Exception {
        // 客户端去操作hdfs时是有一个用户身份的，默认情况下hdfs客户端api会从jvm中获取一个参数作为自己的用户身份 DHADOOP_USER_NAME=hadoop
//        FileSystem hdfs = FileSystem.get(getHdfsConfig()); //默认获取
//        也可以在构造客户端fs对象时，通过参数传递进去
        FileSystem fileSystem = FileSystem.get(new URI(hdfsPath), getConfiguration(), hdfsName);
        return fileSystem;
    }
    @PostConstruct
    public void getPath() {
        hdfsPath = this.hdfs_path;
    }
    @PostConstruct
    public void getName() {
        hdfsName = this.hdfs_name;
    }

    public static String getHdfsPath() {
        return hdfsPath;
    }

    public String getUsername() {
        return hdfs_name;
    }
}

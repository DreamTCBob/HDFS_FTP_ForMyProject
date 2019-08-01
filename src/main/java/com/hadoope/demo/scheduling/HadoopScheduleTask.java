package com.hadoope.demo.scheduling;

import com.hadoope.demo.util.HadoopUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
@EnableAsync
public class HadoopScheduleTask {
    @Value("${FTPFileURL}")
    private String FTPFileURL;
    @Value("${FTPFilePath}")
    private String FTPFilePath;
    private static Logger logger = Logger.getLogger(HadoopScheduleTask.class);
    @Async
    @Scheduled(fixedDelay = 1000)
    public void first() throws IOException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        System.out.print(date.getTime());
//        FTPClient ftpClient = new FTPClient();
//        List<File> files = new ArrayList<>();
//        try {
//            ftpClient.connect(FTPFileURL,21);
//            ftpClient.login("root", "nishipig2/");
//            int reply = ftpClient.getReplyCode();
//            if(!FTPReply.isPositiveCompletion(reply)){
//                ftpClient.disconnect();
//            }
//            ftpClient.changeWorkingDirectory(FTPFilePath);
//            FTPFile[] ftpFiles = ftpClient.listFiles();
//            for(FTPFile ftpFile : ftpFiles){
//                if(ftpFile.getName().charAt(7) == '8'){
//                    File localFile = new File("D:/IDEA/loadFile" + "/" + ftpFile.getName());
//                    OutputStream outputStream = new FileOutputStream(localFile);
//                    ftpClient.retrieveFile(ftpFile.getName(), outputStream);
//                    outputStream.close();
//                }
//            }
//            ftpClient.logout();
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            if(ftpClient.isConnected()){
//                try{
//                    ftpClient.disconnect();
//                }catch (IOException io){
//                }
//            }
//        }
//        logger.info("有如下文件需要采集" + files.toString());
//        //移动文件至待上传临时目录
//        try {
//            File toupload = new File("D:/IDEA/loadFile");
//            //输出日志信息
//            logger.info("文件目录:" + toupload.getAbsolutePath());
//
//            //建立hdfs访问,将临时目录内文件上传至hdfs文件系统
//            FileSystem fs = HadoopUtil.getFileSystem();
//            File[] toupload_List = toupload.listFiles();
//            //上传文件至hdfs文件
//            for(File load_file:toupload_List) {
//                Path hdfs_dst = new Path(dateString.substring(0,10) + "/" + load_file.getName());
//                fs.copyFromLocalFile(new Path(load_file.getAbsolutePath()), hdfs_dst);
//                logger.info("图片上传至HDFS文件目录:" + hdfs_dst);
//            }
//            fs.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        System.out.println();
    }
}

package com.hadoope.demo.service.serviceImpl;

import com.hadoope.demo.service.HadoopService;
import com.hadoope.demo.util.HadoopUtil;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class HadoopServiceImpl implements HadoopService {
    @Override
    public String mkdir(String path) throws Exception{
        // 文件对象
        FileSystem fs = HadoopUtil.getFileSystem();
        // 目标路径
        Path newPath = new Path(path);
        // 创建空文件夹
        boolean isOk = fs.mkdirs(newPath);
        fs.close();
        if (isOk) {
            return "create dir success";
        } else {
            return "create dir fail";
        }
    }
    @Override
    public String createFile(String path, MultipartFile file) throws Exception{
        long startTime=System.currentTimeMillis();//获取开始时间
//        String fileName = file.getOriginalFilename();
//        FileSystem fs = HadoopUtil.getFileSystem();
//        for(int i = 0 ; i < 100 ; i++) {
//            Path newPath = new Path(path + "/" + fileName);
//            // 打开一个输出流
//            FSDataOutputStream outputStream = fs.create(newPath);
//
//            outputStream.write(file.getBytes());
//            outputStream.close();
//        }
//        fs.close();
        for(int i = 0 ; i < 100 ; i++) {
            String fileName = file.getOriginalFilename();
            FileSystem fs = HadoopUtil.getFileSystem();
            Path newPath = new Path(path + "/" + fileName);
            // 打开一个输出流
            FSDataOutputStream outputStream = fs.create(newPath);

            outputStream.write(file.getBytes());
            outputStream.close();
            fs.close();
        }
        long endTime=System.currentTimeMillis();//获取结束时间
        System.out.println("运行时间是："+Math.abs(startTime-endTime)/1000 +"s");//输出运行时间
        return "create file success";
    }
}

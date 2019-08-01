package com.hadoope.demo.controller;

import com.hadoope.demo.service.HadoopService;
import com.hadoope.demo.util.HadoopUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/hadoop")
public class HadoopController {
    @Autowired
    private HadoopService hadoopService;
    private static Logger logger = Logger.getLogger(HadoopController.class);
    @PostMapping("/mkdir")
    public String mkdir(@RequestParam("path") String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return "请求参数为空";
        }
        return hadoopService.mkdir(path);
    }

    @PostMapping("/createFile")
    public String createFile(@RequestParam("path") String path, @RequestParam("file")MultipartFile file) throws Exception{
        if(path.equals("")) return "path is not exit";
        logger.info("新建文件成功");
        return hadoopService.createFile(path, file);
    }

    @PostMapping("/deleteFile")
    public String deleteFile(@RequestParam("path") String path) throws Exception {
        FileSystem fs = HadoopUtil.getFileSystem();
        Path newPath = new Path(path);
        boolean isOk = fs.deleteOnExit(newPath);
        fs.close();
        if (isOk) {
            return "delete file success";
        } else {
            return "delete file fail";
        }
    }
}

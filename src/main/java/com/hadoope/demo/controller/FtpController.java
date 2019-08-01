package com.hadoope.demo.controller;

import com.hadoope.demo.service.FtpService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

@RestController
public class FtpController {

    @Autowired
    private FtpService ftpService;

    //文件上传
    @PostMapping("/createFile")
    public JSONObject createFile(@RequestParam("fileName") String fileName,///此处把电话号码当作上传file的唯一name
                                 @RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        JSONObject jsonObject = new JSONObject();
        try {
            long startTime=System.currentTimeMillis();//获取开始时间
            int flag = 0;
            for(int i = 0 ; i < 100 ; i++) {
                InputStream inputStream = multipartFile.getInputStream();
                flag = ftpService.createImageFile(fileName, inputStream);
            }
            if (flag == 0) {
                jsonObject.put("error", 1);
                jsonObject.put("status", "读取文件失败");
            } else if (flag == 1) {
                jsonObject.put("error", 0);
                jsonObject.put("status", "上传文件成功");
            } else {
                jsonObject.put("error", 2);
                jsonObject.put("status", "上传文件失败");
            }
            long endTime=System.currentTimeMillis();//获取结束时间
            System.out.println("运行时间是："+Math.abs(startTime-endTime)/1000 +"s");//输出运行时间
        }catch (Exception ex){
            jsonObject.put("isAllowed",false);
        }
        return jsonObject;
    }
}


package com.hadoope.demo.service.serviceImpl;

import com.hadoope.demo.service.FtpService;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;


@Service
public class FtpServiceImpl implements FtpService {
    @Value("${FTPFileURL}")
    private String ftpFileURL;
    @Value("${FTPUserName}")
    private String ftpUserName;
    @Value("${FTPUserPassword}")
    private String ftpUserPassword;
    @Value("${FTPFilePath}")
    private String ftpFilePath;
    @Override
    public int createImageFile(String fileName, InputStream inputStream) throws IOException{
        if(StringUtils.isEmpty(fileName) || inputStream == null){
            return 0;
        }
        fileName = fileName + ".exe";
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("GBK");
        try {
            int reply;
            ftp.connect(ftpFileURL,21);
            ftp.login(ftpUserName,ftpUserPassword);
            reply = ftp.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply)){
                ftp.disconnect();
                return 2;
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.makeDirectory(ftpFilePath);
            ftp.changeWorkingDirectory(ftpFilePath);
            ftp.storeFile(fileName,inputStream);
            inputStream.close();
            ftp.logout();
            return 1;
        }catch (IOException ex){
            return 2;
        }finally {
            if(ftp.isConnected()){
                try{
                    ftp.disconnect();
                }catch (IOException ioe){}
            }
        }
    }
}


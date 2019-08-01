package com.hadoope.demo.service;
import java.io.IOException;
import java.io.InputStream;

public interface FtpService {
    int createImageFile(String fileName, InputStream inputStream) throws IOException;
}

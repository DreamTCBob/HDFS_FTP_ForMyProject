package com.hadoope.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface HadoopService {
    String mkdir(String path) throws Exception;
    String createFile(String path, MultipartFile file) throws Exception;
}

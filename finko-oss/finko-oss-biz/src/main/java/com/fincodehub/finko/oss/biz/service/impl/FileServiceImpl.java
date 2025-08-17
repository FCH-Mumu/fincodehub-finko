package com.fincodehub.finko.oss.biz.service.impl;

import com.fincodehub.finko.oss.biz.service.FileService;
import com.fincodehub.finko.oss.biz.strategy.FileStrategy;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @title FileServiceImpl
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/17 12:22
 * @description <TODO description class purpose>
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Resource
    private FileStrategy fileStrategy;


    // private static final String BUCKET_NAME = "finko";

    @Override
    public ResponseObject<?> uploadFile(MultipartFile file) {
        // 上传文件
        String url = fileStrategy.uploadFile(file);

        return ResponseObject.success(url);
    }
}
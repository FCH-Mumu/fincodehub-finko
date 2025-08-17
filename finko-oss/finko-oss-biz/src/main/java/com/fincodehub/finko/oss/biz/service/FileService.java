package com.fincodehub.finko.oss.biz.service;

import com.finko.framework.common.response.ResponseObject;
import org.springframework.web.multipart.MultipartFile;

/**
 * @title FileService
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/17 12:21
 * @description <TODO description class purpose>
 */
public interface FileService {
    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    ResponseObject<?> uploadFile(MultipartFile file);
}

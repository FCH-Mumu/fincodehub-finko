package com.fincodehub.finko.oss.biz.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * @title FileStrategy
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/17 12:10
 * @description 文件策略接口
 */
public interface FileStrategy {
    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);
}

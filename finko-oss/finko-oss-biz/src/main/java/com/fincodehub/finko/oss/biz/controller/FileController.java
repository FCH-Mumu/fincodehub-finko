package com.fincodehub.finko.oss.biz.controller;

import com.fincodehub.finko.oss.biz.service.FileService;
import com.finko.framework.biz.context.holder.LoginUserContextHolder;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @title FileController
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/17 12:24
 * @description <TODO description class purpose>
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseObject<?> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        log.info("=============> 上传文件，当前用户ID，{}", LoginUserContextHolder.getUserId());
        return fileService.uploadFile(file);
    }

}

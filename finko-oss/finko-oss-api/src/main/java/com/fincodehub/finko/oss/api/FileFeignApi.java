package com.fincodehub.finko.oss.api;

/**
 * @title FileFeignApi
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/23 20:17
 * @description <TODO description class purpose>
 */

import com.fincodehub.finko.oss.constant.ApiConstants;
import com.finko.framework.common.response.ResponseObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = ApiConstants.SERVICE_NAME)
public interface FileFeignApi {
    String PREFIX = "/file";
    @PostMapping(value = PREFIX + "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseObject<?> uploadFile(@RequestPart(value = "file") MultipartFile file);
}

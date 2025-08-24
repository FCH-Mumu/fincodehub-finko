package com.fincodehub.finko.user.biz.rpc;

import com.fincodehub.finko.oss.api.FileFeignApi;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @title OssRpcService
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/23 21:19
 * @description 对象存储服务调用
 */
@Component
public class OssRpcService {
    @Resource
    private FileFeignApi fileFeignApi;

    public String uploadFile(MultipartFile file) {
        // 调用对象存储服务上传文件
        ResponseObject<?> responseObject = fileFeignApi.uploadFile(file);
        if (!responseObject.isSuccess()){
            return null;
        }
        // 返回图片访问链接
        return (String) responseObject.getData();
    }
}

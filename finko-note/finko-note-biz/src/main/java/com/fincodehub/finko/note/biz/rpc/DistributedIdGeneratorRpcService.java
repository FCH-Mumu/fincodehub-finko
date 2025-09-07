package com.fincodehub.finko.note.biz.rpc;

import com.fincodehub.finko.distributed.id.generator.api.DistributedIdGeneratorFeignApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @title DistributedIdGeneratorRpcService
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/6 17:43
 * @description <TODO description class purpose>
 */
@Component
public class DistributedIdGeneratorRpcService {
    @Resource
    private DistributedIdGeneratorFeignApi distributedIdGeneratorFeignApi;

    /**
     * 获取雪花算法ID
     *
     * @return 雪花算法ID
     */
    public String getSnowflakeId() {
        return distributedIdGeneratorFeignApi.getSnowflakeId("test");
    }
}

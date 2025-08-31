package com.fincodehub.finko.user.biz.rpc;

import com.fincodehub.finko.distributed.id.generator.api.DistributedIdGeneratorFeignApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @title DistributedIdGeneratorRpcService
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/31 21:25
 * @description: 分布式 ID 生成服务
 **/
@Component
public class DistributedIdGeneratorRpcService {

    @Resource
    private DistributedIdGeneratorFeignApi distributedIdGeneratorFeignApi;

    /**
     * Leaf 号段模式：finko ID 业务标识
     */
    private static final String BIZ_TAG_FINKO_ID = "leaf-segment-finko-id";

    /**
     * 调用分布式 ID 生成服务生成finko ID
     *
     * @return
     */
    public String getFinkoId() {
        return distributedIdGeneratorFeignApi.getSegmentId(BIZ_TAG_FINKO_ID);
    }

    /**
     * Leaf 号段模式：用户 ID 业务标识
     */
    private static final String BIZ_TAG_USER_ID = "leaf-segment-user-id";

    // 省略...

    /**
     * 调用分布式 ID 生成服务用户 ID
     *
     * @return
     */
    public String getUserId() {
        return distributedIdGeneratorFeignApi.getSegmentId(BIZ_TAG_USER_ID);
    }
}
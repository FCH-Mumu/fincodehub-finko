package com.fincodehub.finko.kv.api;

import com.fincodehub.finko.kv.constant.ApiConstants;
import com.fincodehub.finko.kv.dto.req.AddNoteContentReqDTO;
import com.fincodehub.finko.kv.dto.req.DeleteNoteContentReqDTO;
import com.fincodehub.finko.kv.dto.req.FindNoteContentReqDTO;
import com.fincodehub.finko.kv.dto.rsp.FindNoteContentRspDTO;
import com.finko.framework.common.response.ResponseObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @title KeyValueFeignApi
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/30 21:41
 * @description K-V 键值存储Feign接口
 */
@FeignClient(name = ApiConstants.SERVICE_NAME)
public interface KeyValueFeignApi {
    String PREFIX = "/kv";

    @PostMapping(PREFIX + "/note/content/add")
    ResponseObject<?> addNoteContent(@RequestBody AddNoteContentReqDTO addNoteContentReqDTO);

    @PostMapping(value = PREFIX + "/note/content/find")
    ResponseObject<FindNoteContentRspDTO> findNoteContent(@RequestBody FindNoteContentReqDTO findNoteContentReqDT);

    @PostMapping(value = PREFIX + "/note/content/delete")
    ResponseObject<?> deleteNoteContent(@RequestBody DeleteNoteContentReqDTO deleteNoteContentReqDTO);
}

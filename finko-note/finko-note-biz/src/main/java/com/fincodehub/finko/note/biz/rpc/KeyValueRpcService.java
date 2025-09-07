package com.fincodehub.finko.note.biz.rpc;

import com.fincodehub.finko.kv.api.KeyValueFeignApi;
import com.fincodehub.finko.kv.dto.req.AddNoteContentReqDTO;
import com.fincodehub.finko.kv.dto.req.DeleteNoteContentReqDTO;
import com.fincodehub.finko.kv.dto.req.FindNoteContentReqDTO;
import com.fincodehub.finko.kv.dto.rsp.FindNoteContentRspDTO;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 * @title KeyValueRpcService
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/6 17:47
 * @description KV服务
 */
@Component
public class KeyValueRpcService {
    @Resource
    private KeyValueFeignApi keyValueFeignApi;

    /**
     * 保存笔记内容
     *
     * @param uuid
     * @param content
     * @return
     */
    public boolean saveNoteContent(String uuid, String content) {
        AddNoteContentReqDTO addNoteContentReqDTO = new AddNoteContentReqDTO();
        addNoteContentReqDTO.setUuid(uuid);
        addNoteContentReqDTO.setContent(content);
        ResponseObject<?> responseObject = keyValueFeignApi.addNoteContent(addNoteContentReqDTO);
        if (Objects.isNull(responseObject) || !responseObject.isSuccess()){
            return false;
        }
        return true;
    }

    /**
     * 删除笔记内容
     * @param uuid
     * @return
     */
    public boolean deleteNoteContent(String uuid) {
        DeleteNoteContentReqDTO deleteNoteContentReqDTO = new DeleteNoteContentReqDTO();
        deleteNoteContentReqDTO.setUuid(uuid);
        ResponseObject<?> responseObject = keyValueFeignApi.deleteNoteContent(deleteNoteContentReqDTO);
        if (Objects.isNull(responseObject) || !responseObject.isSuccess()){
            return false;
        }
        return true;
    }

    public String findNoteContent(String uuid) {
        FindNoteContentReqDTO findNoteContentReqDTO = new FindNoteContentReqDTO();
        findNoteContentReqDTO.setUuid(uuid);

        ResponseObject<FindNoteContentRspDTO> responseObject = keyValueFeignApi.findNoteContent(findNoteContentReqDTO);
        if (Objects.isNull(responseObject) || !responseObject.isSuccess() || Objects.isNull(responseObject.getData())){
            return null;
        }
        return responseObject.getData().getContent();
    }
}

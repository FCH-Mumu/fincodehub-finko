package com.fincodehub.finko.kv.sevice;

import com.fincodehub.finko.kv.dto.req.AddNoteContentReqDTO;
import com.fincodehub.finko.kv.dto.req.DeleteNoteContentReqDTO;
import com.fincodehub.finko.kv.dto.req.FindNoteContentReqDTO;
import com.fincodehub.finko.kv.dto.rsp.FindNoteContentRspDTO;
import com.finko.framework.common.response.ResponseObject;

/**
 * @title NoteContentService
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/30 21:19
 * @description: 笔记内容存储业务
 **/
public interface NoteContentService {

    /**
     * 添加笔记内容
     *
     * @param addNoteContentReqDTO
     * @return
     */
    ResponseObject<?> addNoteContent(AddNoteContentReqDTO addNoteContentReqDTO);
    /**
     * 查询笔记内容
     *
     * @param findNoteContentReqDTO
     * @return
     */
    ResponseObject<FindNoteContentRspDTO> findNoteContent(FindNoteContentReqDTO findNoteContentReqDTO);

    /**
     * 删除笔记内容
     *
     * @param deleteNoteContentReqDTO
     * @return
     */
    ResponseObject<?> deleteNoteContent(DeleteNoteContentReqDTO deleteNoteContentReqDTO);

    
}

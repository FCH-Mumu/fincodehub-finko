package com.fincodehub.finko.kv.controller;

import com.fincodehub.finko.kv.dto.req.AddNoteContentReqDTO;
import com.fincodehub.finko.kv.dto.req.DeleteNoteContentReqDTO;
import com.fincodehub.finko.kv.dto.req.FindNoteContentReqDTO;
import com.fincodehub.finko.kv.dto.rsp.FindNoteContentRspDTO;
import com.fincodehub.finko.kv.sevice.NoteContentService;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title NoteContentController
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/30 21:25
 * @description <TODO description class purpose>
 */
@RestController
@RequestMapping("/kv")
@Slf4j
public class NoteContentController {

    @Resource
    private NoteContentService noteContentService;

    @PostMapping("/note/content/add")
    public ResponseObject <?> addNoteContent(@Validated @RequestBody AddNoteContentReqDTO addNoteContentReqDTO) {
        return noteContentService.addNoteContent(addNoteContentReqDTO);
    }

    @PostMapping(value = "/note/content/find")
    public ResponseObject<FindNoteContentRspDTO> findNoteContent(@Validated @RequestBody FindNoteContentReqDTO findNoteContentReqDTO) {
        return noteContentService.findNoteContent(findNoteContentReqDTO);
    }
    @PostMapping(value = "/note/content/delete")
    public ResponseObject<?> deleteNoteContent(@Validated @RequestBody DeleteNoteContentReqDTO deleteNoteContentReqDTO) {
        return noteContentService.deleteNoteContent(deleteNoteContentReqDTO);
    }
}

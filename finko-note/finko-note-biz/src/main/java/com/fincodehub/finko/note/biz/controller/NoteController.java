package com.fincodehub.finko.note.biz.controller;

import com.fincodehub.finko.note.biz.model.vo.DeleteNoteReqVO;
import com.fincodehub.finko.note.biz.model.vo.FindNoteDetailReqVO;
import com.fincodehub.finko.note.biz.model.vo.FindNoteDetailRspVO;
import com.fincodehub.finko.note.biz.model.vo.PublishNoteReqVO;
import com.fincodehub.finko.note.biz.model.vo.TopNoteReqVO;
import com.fincodehub.finko.note.biz.model.vo.UpdateNoteReqVO;
import com.fincodehub.finko.note.biz.model.vo.UpdateNoteVisibleOnlyMeReqVO;
import com.fincodehub.finko.note.biz.service.INoteService;
import com.finko.framework.biz.operationlog.aspect.ApiOperationLog;
import com.finko.framework.common.response.ResponseObject;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 笔记表 前端控制器
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-09-06
 */
@RestController
@RequestMapping("/note")
@Slf4j
public class NoteController {

    @Resource
    private INoteService noteService;

    @PostMapping(value = "/publish")
    @ApiOperationLog(description = "笔记发布")
    public ResponseObject<?> publishNote(@Validated @RequestBody PublishNoteReqVO publishNoteReqVO) {
        return noteService.publishNote(publishNoteReqVO);
    }
    @PostMapping(value = "/detail")
    @ApiOperationLog(description = "笔记详情")
    public ResponseObject<FindNoteDetailRspVO> findNoteDetail(@Validated @RequestBody FindNoteDetailReqVO findNoteDetailReqVO) {
        return noteService.findNoteDetail(findNoteDetailReqVO);
    }

    @PostMapping(value = "/update")
    @ApiOperationLog(description = "笔记修改")
    public ResponseObject<?> updateNote(@Validated @RequestBody UpdateNoteReqVO updateNoteReqVO) {
        return noteService.updateNote(updateNoteReqVO);
    }

    @PostMapping(value = "/delete")
    @ApiOperationLog(description = "删除笔记")
    public ResponseObject<?> deleteNote(@Validated @RequestBody DeleteNoteReqVO deleteNoteReqVO) {
        return noteService.deleteNote(deleteNoteReqVO);
    }

    @PostMapping(value = "/visible/onlyme")
    @ApiOperationLog(description = "笔记仅对自己可见")
    public ResponseObject<?> visibleOnlyMe(@Validated @RequestBody UpdateNoteVisibleOnlyMeReqVO updateNoteVisibleOnlyMeReqVO) {
        return noteService.visibleOnlyMe(updateNoteVisibleOnlyMeReqVO);
    }

    @PostMapping(value = "/top")
    @ApiOperationLog(description = "置顶/取消置顶笔记")
    public ResponseObject<?> topNote(@Validated @RequestBody TopNoteReqVO topNoteReqVO) {
        return noteService.topNote(topNoteReqVO);
    }
}

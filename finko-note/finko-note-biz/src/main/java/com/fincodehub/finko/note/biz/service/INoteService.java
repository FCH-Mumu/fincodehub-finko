package com.fincodehub.finko.note.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fincodehub.finko.note.biz.domain.dataobject.Note;
import com.fincodehub.finko.note.biz.model.vo.DeleteNoteReqVO;
import com.fincodehub.finko.note.biz.model.vo.FindNoteDetailReqVO;
import com.fincodehub.finko.note.biz.model.vo.FindNoteDetailRspVO;
import com.fincodehub.finko.note.biz.model.vo.PublishNoteReqVO;
import com.fincodehub.finko.note.biz.model.vo.TopNoteReqVO;
import com.fincodehub.finko.note.biz.model.vo.UpdateNoteReqVO;
import com.fincodehub.finko.note.biz.model.vo.UpdateNoteVisibleOnlyMeReqVO;
import com.finko.framework.common.response.ResponseObject;

/**
 * <p>
 * 笔记表 服务类
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-09-06
 */
public interface INoteService extends IService<Note> {
    /**
     * 笔记发布
     * @param publishNoteReqVO
     * @return
     */
    ResponseObject<?> publishNote(PublishNoteReqVO publishNoteReqVO);

    /**
     * 笔记详情
     * @param findNoteDetailReqVO
     * @return
     */
    ResponseObject<FindNoteDetailRspVO> findNoteDetail(FindNoteDetailReqVO findNoteDetailReqVO);

    /**
     * 笔记更新
     * @param updateNoteReqVO
     * @return
     */
    ResponseObject<?> updateNote(UpdateNoteReqVO updateNoteReqVO);

    /**
     * 删除本地笔记缓存
     * @param noteId
     */
    void deleteNoteLocalCache(Long noteId);

    /**
     * 删除笔记
     * @param deleteNoteReqVO
     * @return
     */
    ResponseObject<?> deleteNote(DeleteNoteReqVO deleteNoteReqVO);

    /**
     * 笔记仅对自己可见
     * @param updateNoteVisibleOnlyMeReqVO
     * @return
     */
    ResponseObject<?> visibleOnlyMe(UpdateNoteVisibleOnlyMeReqVO updateNoteVisibleOnlyMeReqVO);
    /**
     * 笔记置顶 / 取消置顶
     * @param topNoteReqVO
     * @return
     */
    ResponseObject<?> topNote(TopNoteReqVO topNoteReqVO);
}

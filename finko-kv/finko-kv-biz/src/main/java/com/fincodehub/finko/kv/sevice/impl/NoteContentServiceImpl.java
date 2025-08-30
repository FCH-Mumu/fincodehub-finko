package com.fincodehub.finko.kv.sevice.impl;

import com.fincodehub.finko.kv.domain.dataobject.NoteContentDO;
import com.fincodehub.finko.kv.domain.repository.NoteContentRepository;
import com.fincodehub.finko.kv.dto.req.AddNoteContentReqDTO;
import com.fincodehub.finko.kv.dto.req.DeleteNoteContentReqDTO;
import com.fincodehub.finko.kv.dto.req.FindNoteContentReqDTO;
import com.fincodehub.finko.kv.dto.rsp.FindNoteContentRspDTO;
import com.fincodehub.finko.kv.enums.ResponseCodeEnum;
import com.fincodehub.finko.kv.sevice.NoteContentService;
import com.finko.framework.common.exception.BizException;
import com.finko.framework.common.response.ResponseObject;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @title NoteContentServiceImpl
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/30 21:20
 * @description <TODO description class purpose>
 */
@Service
@Slf4j
public class NoteContentServiceImpl implements NoteContentService {

  private final NoteContentRepository noteContentRepository;

  public NoteContentServiceImpl(NoteContentRepository noteContentRepository) {
    this.noteContentRepository = noteContentRepository;
  }

  @Override
  public ResponseObject<?> addNoteContent(AddNoteContentReqDTO addNoteContentReqDTO) {
    // 笔记ID
    String noteId = addNoteContentReqDTO.getNoteId();
    // 笔记内容
    String content = addNoteContentReqDTO.getContent();

    // 构建数据库DO实体类
    NoteContentDO nodeContent =
        NoteContentDO.builder().id(UUID.randomUUID()).content(content).build();

    // 插入数据
    noteContentRepository.save(nodeContent);
    return ResponseObject.success();
  }

  @Override
  public ResponseObject<FindNoteContentRspDTO> findNoteContent(
      FindNoteContentReqDTO findNoteContentReqDTO) {
    // ID
    String noteId = findNoteContentReqDTO.getNoteId();
    // 根据ID查询笔记内容
    Optional<NoteContentDO> optional = noteContentRepository.findById(UUID.fromString(noteId));
    // 若笔记不存在
    if (!optional.isPresent()) {
      throw new BizException(ResponseCodeEnum.NOTE_CONTENT_NOT_FOUND);
    }
    NoteContentDO noteContentDO = optional.get();
    // 构建返参 DTO
    FindNoteContentRspDTO findNoteContentRspDTO =
        FindNoteContentRspDTO.builder()
            .noteId(noteContentDO.getId())
            .content(noteContentDO.getContent())
            .build();

    return ResponseObject.success(findNoteContentRspDTO);
  }

  /**
   * 删除笔记内容
   *
   * @param deleteNoteContentReqDTO
   * @return
   */
  @Override
  public ResponseObject<?> deleteNoteContent(DeleteNoteContentReqDTO deleteNoteContentReqDTO) {
    // 笔记 ID
    String noteId = deleteNoteContentReqDTO.getNoteId();
    // 删除笔记内容
    noteContentRepository.deleteById(UUID.fromString(noteId));

    return ResponseObject.success();
  }
}

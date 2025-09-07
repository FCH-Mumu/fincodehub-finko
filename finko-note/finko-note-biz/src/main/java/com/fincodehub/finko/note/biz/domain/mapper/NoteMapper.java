package com.fincodehub.finko.note.biz.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fincodehub.finko.note.biz.domain.dataobject.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 笔记表 Mapper 接口
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-09-06
 */
@Mapper
public interface NoteMapper extends BaseMapper<Note> {


    @Select("select * from sys_note where id = #{id} and status = 1")
    Note selectByPrimaryKey(Long noteId);

    // @Update("update sys_note set title = #{title},is_content_empty = #{isContentEmpty},topic_id = #{topicId},topic_name = #{topicName},`type` = #{type},img_uris = #{imgUris},video_uri = #{videoUri},update_time = #{updateTime} where id = #{id}")
    // void updateByPrimaryKey(Note note);
}

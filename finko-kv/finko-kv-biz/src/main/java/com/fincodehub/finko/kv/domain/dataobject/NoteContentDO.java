package com.fincodehub.finko.kv.domain.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


import java.util.UUID;

/**
 * @title NoteContentDO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/30 10:12
 * @description: 笔记内容
 **/
@Table("note_content")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteContentDO {

    @PrimaryKey("id")
    private UUID id;

    private String content;
}

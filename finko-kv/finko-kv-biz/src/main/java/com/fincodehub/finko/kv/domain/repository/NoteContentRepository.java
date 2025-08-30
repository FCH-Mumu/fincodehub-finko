package com.fincodehub.finko.kv.domain.repository;

import com.fincodehub.finko.kv.domain.dataobject.NoteContentDO;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @title NoteContentRepository
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/30 10:13
 * @description <TODO description class purpose>
 */
@Repository
public interface NoteContentRepository extends CassandraRepository<NoteContentDO, UUID> {

}
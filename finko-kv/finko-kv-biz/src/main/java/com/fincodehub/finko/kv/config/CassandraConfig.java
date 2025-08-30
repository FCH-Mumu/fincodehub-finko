package com.fincodehub.finko.kv.config;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;

/**
 * @title CassandraConfig
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/8/30 10:22
 * @description: Cassandra 配置类  extends AbstractCassandraConfiguration 
 **/
@Configuration
public class CassandraConfig {

    @Value("${spring.cassandra.keyspace-name}")
    private String keySpace;

    @Value("${spring.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.cassandra.port}")
    private int port;

    @Value("${spring.cassandra.local-datacenter}")
    private String localDatacenter;

    // 创建 CqlSession Bean（名字唯一）
    @Bean
    public CqlSession cqlSession() {
        return CqlSession.builder()
                .withKeyspace(keySpace)
                .addContactPoint(java.net.InetSocketAddress.createUnresolved(contactPoints, port))
                .withLocalDatacenter(localDatacenter)
                .build();
    }

    // 显式声明 CassandraConverter
    @Bean
    public CassandraConverter cassandraConverter(CassandraMappingContext mappingContext) {
        return new MappingCassandraConverter(mappingContext);
    }

    @Bean
    public CassandraMappingContext cassandraMappingContext() {
        return new CassandraMappingContext();
    }

    @Bean
    public CassandraTemplate cassandraTemplate(CqlSession cqlSession, CassandraConverter converter) {
        return new CassandraTemplate(cqlSession, converter);
    }

}
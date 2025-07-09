package com.fincodehub.finko.auth.domain.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @title UserDO
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/9 21:22
 * @description <TODO description class purpose>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDO {

    private Long id;
    private String username;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
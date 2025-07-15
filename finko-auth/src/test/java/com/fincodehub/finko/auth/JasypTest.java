package com.fincodehub.finko.auth;

import com.finko.framework.common.util.JasyptUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @title JasypTest
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/15 8:08
 * @description <TODO description class purpose>
 */
@SpringBootTest
@Slf4j
public class JasypTest {
    
    @Test
    public void jasypTest() {
        log.info("password: {}", JasyptUtils.encrypt("", ""));
        log.info("password: {}", JasyptUtils.encrypt("", ""));
    }
}

package org.zpp.security.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.zpp.security.core.properties.SecurityProperties;

/**
 * @author zpp
 * @date 2019/1/4 14:10
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig {

}

package com.kareegar.mmfb.frontend2.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties are configured in the {@code application.yml} file.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
}

package com.paradise.core.common.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Paradise
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "minio")
public class MinIoConfiguration {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String openUrl;
}

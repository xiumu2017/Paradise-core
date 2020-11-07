package com.paradise.core.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Paradise
 */
@Data
@AllArgsConstructor
@Builder
public class MinIoConfiguration {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String openUrl;
}

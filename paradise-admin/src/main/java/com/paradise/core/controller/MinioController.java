package com.paradise.core.controller;

import com.paradise.core.common.api.Result;
import com.paradise.core.common.domain.MinIoConfiguration;
import com.paradise.core.common.service.impl.MinIoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * MinIO对象存储管理
 *
 * @author Paradise
 * @date 2019/12/25
 */
@Slf4j
@Api(tags = "MinIO对象存储管理")
@RestController
@RequestMapping("/minio")
public class MinioController {

    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.bucketName}")
    private String bucketName;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;
    @Value("${minio.openUrl}")
    private String openUrl;

    private final MinIoService minIoService;

    public MinioController(MinIoService minIoService) {
        this.minIoService = minIoService;
    }

    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload(@RequestParam("file") MultipartFile file) {
        try {
            MinIoConfiguration configuration = new MinIoConfiguration(endpoint, accessKey, secretKey, bucketName, openUrl);
            return minIoService.upload(file, configuration);
        } catch (Exception e) {
            log.info("上传发生错误: {}！", e.getMessage(), e);
        }
        return Result.failed();
    }

    @ApiOperation("文件删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestParam("objectName") String objectName) {
        return minIoService.delete(objectName, new MinIoConfiguration(endpoint, accessKey, secretKey, bucketName, openUrl));
    }
}

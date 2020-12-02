package com.paradise.core.app.controller;

import com.paradise.core.common.api.Result;
import com.paradise.core.common.domain.MinioUploadDto;
import com.paradise.core.common.service.impl.MinIoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

/**
 * MinIO对象存储管理
 *
 * @author Paradise
 * @date 2019/12/25
 */
@ApiIgnore
@Slf4j
@Api(tags = "App图片上传服务", hidden = true)
@RestController
@RequestMapping("/oss")
public class AppOssController {

    private final MinIoService minIoService;

    public AppOssController(MinIoService minIoService) {
        this.minIoService = minIoService;
    }

    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result<MinioUploadDto> upload(@RequestParam("file") MultipartFile file) {
        try {
            return minIoService.upload(file);
        } catch (Exception e) {
            log.info("上传发生错误: {}！", e.getMessage(), e);
        }
        return Result.failed();
    }

    @ApiOperation("文件删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<Object> delete(@RequestParam("objectName") String objectName) {
        return minIoService.delete(objectName);
    }
}

package com.paradise.core.common.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.paradise.core.common.api.Result;
import com.paradise.core.common.domain.BucketPolicyConfigDto;
import com.paradise.core.common.domain.MinIoConfiguration;
import com.paradise.core.common.domain.MinioUploadDto;
import com.paradise.core.common.utils.DateUtil;
import com.paradise.core.common.utils.GeneratorUtil;
import io.minio.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Paradise
 */
@Slf4j
@Component
@AllArgsConstructor
@EnableConfigurationProperties(MinIoConfiguration.class)
public class MinIoService {

    private final MinIoConfiguration minIoConfiguration;

    public com.paradise.core.common.api.Result<MinioUploadDto> upload(MultipartFile file) {
        try {
            //创建一个MinIO的Java客户端
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minIoConfiguration.getEndpoint())
                    .credentials(minIoConfiguration.getAccessKey(), minIoConfiguration.getSecretKey())
                    .build();
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minIoConfiguration.getBucketName()).build());
            if (!isExist) {
                //创建存储桶并设置只读权限
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minIoConfiguration.getBucketName()).build());
                BucketPolicyConfigDto bucketPolicyConfigDto = createBucketPolicyConfigDto(minIoConfiguration.getBucketName());
                SetBucketPolicyArgs setBucketPolicyArgs = SetBucketPolicyArgs.builder()
                        .bucket(minIoConfiguration.getBucketName())
                        .config(JSONUtil.toJsonStr(bucketPolicyConfigDto))
                        .build();
                minioClient.setBucketPolicy(setBucketPolicyArgs);
            }
            String filename = fileNamePrefix() + file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // 设置存储对象名称
            String objectName = sdf.format(new Date()) + "/" + filename;
            // 使用putObject上传一个文件到存储桶中
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minIoConfiguration.getBucketName())
                    .object(objectName)
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), ObjectWriteArgs.MIN_MULTIPART_SIZE).build();
            minioClient.putObject(putObjectArgs);
            MinioUploadDto minioUploadDto = new MinioUploadDto();
            minioUploadDto.setName(filename);
            minioUploadDto.setUrl(minIoConfiguration.getOpenUrl() + "/" + minIoConfiguration.getBucketName() + "/" + objectName);
            log.info("文件上传成功:{}", minioUploadDto.getUrl());
            return Result.success(minioUploadDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("上传发生错误: {}！", e.getMessage());
        }
        return Result.failed();
    }

    private String fileNamePrefix() {
        return DateUtil.defaultDateFormat() + GeneratorUtil.getNonceString(2);
    }

    private BucketPolicyConfigDto createBucketPolicyConfigDto(String bucketName) {
        BucketPolicyConfigDto.Statement statement = BucketPolicyConfigDto.Statement.builder()
                .Effect("Allow")
                .Principal("*")
                .Action("s3:GetObject")
                .Resource("arn:aws:s3:::" + bucketName + "/*.**").build();
        return BucketPolicyConfigDto.builder()
                .Version("2012-10-17")
                .Statement(CollUtil.toList(statement))
                .build();
    }

    public Result<Object> delete(String objectName) {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minIoConfiguration.getEndpoint())
                    .credentials(minIoConfiguration.getAccessKey(), minIoConfiguration.getSecretKey())
                    .build();
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(minIoConfiguration.getBucketName()).object(objectName).build());
            return Result.success(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.failed();
    }

}

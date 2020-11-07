package com.paradise.core.common.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.paradise.core.common.api.Result;
import com.paradise.core.common.domain.BucketPolicyConfigDto;
import com.paradise.core.common.domain.MinIoConfiguration;
import com.paradise.core.common.domain.MinioUploadDto;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Paradise
 */
@Slf4j
@Component
public class MinIoService {

    public Result upload(MultipartFile file, MinIoConfiguration minIoConfiguration) {
        try {
            //创建一个MinIO的Java客户端
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minIoConfiguration.getEndpoint())
                    .credentials(minIoConfiguration.getAccessKey(), minIoConfiguration.getSecretKey())
                    .build();
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minIoConfiguration.getBucketName()).build());
            if (isExist) {
                log.info("存储桶已经存在！");
            } else {
                //创建存储桶并设置只读权限
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minIoConfiguration.getBucketName()).build());
                BucketPolicyConfigDto bucketPolicyConfigDto = createBucketPolicyConfigDto(minIoConfiguration.getBucketName());
                SetBucketPolicyArgs setBucketPolicyArgs = SetBucketPolicyArgs.builder()
                        .bucket(minIoConfiguration.getBucketName())
                        .config(JSONUtil.toJsonStr(bucketPolicyConfigDto))
                        .build();
                minioClient.setBucketPolicy(setBucketPolicyArgs);
            }
            String filename = file.getOriginalFilename();
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
            log.info("文件上传成功!");
            MinioUploadDto minioUploadDto = new MinioUploadDto();
            minioUploadDto.setName(filename);
            minioUploadDto.setUrl(minIoConfiguration.getOpenUrl() + "/" + minIoConfiguration.getBucketName() + "/" + objectName);
            return Result.success(minioUploadDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("上传发生错误: {}！", e.getMessage());
        }
        return Result.failed();
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

    public Result delete(String objectName, MinIoConfiguration configuration) {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(configuration.getEndpoint())
                    .credentials(configuration.getAccessKey(), configuration.getSecretKey())
                    .build();
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(configuration.getBucketName()).object(objectName).build());
            return Result.success(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.failed();
    }

}

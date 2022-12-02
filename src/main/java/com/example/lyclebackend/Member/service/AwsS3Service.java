package com.example.lyclebackend.Member.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.example.lyclebackend.Member.util.CommonUtils;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RequiredArgsConstructor
@Service
public class AwsS3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String uploadFileV1(String folder, MultipartFile multipartFile) {
        //validateFileExists(multipartFile);


        String fileName = CommonUtils.buildFileName(folder, multipartFile.getOriginalFilename());
        log.info(fileName);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            objectMetadata.setContentLength(bytes.length);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, byteArrayInputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            log.info("ee");
        }

        return amazonS3Client.getUrl(bucketName, fileName).toString();

    }

//    private void validateFileExists(MultipartFile multipartFile) {
//        if (multipartFile.isEmpty()) {
//            throw new EmptyFileException();
//        }
//    }
}
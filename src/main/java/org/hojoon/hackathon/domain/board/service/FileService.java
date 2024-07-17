package org.hojoon.hackathon.domain.board.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    @Transactional
    public String fileUpload(MultipartFile file) {

        try {

            String fileName = file.getOriginalFilename();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            amazonS3.putObject(
                    new PutObjectRequest(bucket, fileName, file.getInputStream(), metadata)
            );

            return amazonS3.getUrl(bucket, fileName).toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

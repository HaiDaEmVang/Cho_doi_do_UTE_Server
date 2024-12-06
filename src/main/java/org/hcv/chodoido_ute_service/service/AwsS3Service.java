package org.hcv.chodoido_ute_service.service;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AwsS3Service {

    @Value("${aws.s3.access_Key}")
    private String awsS3AccessKey;

    @Value("${aws.s3.secret_Key}")
    private String awsS3SecretKey;

    public String bucketName = "chodoidoute";

//    public String saveImageToS3(MultipartFile photo, String folder) {
//
//        try {
//
//            String s3Filename =  folder + "/" + photo.getOriginalFilename() + "_" + (new Date().getTime());
//
//            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsS3AccessKey, awsS3SecretKey);
//            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                    .withRegion(Regions.AP_SOUTHEAST_1)
//                    .build();
//
//            InputStream inputStream = photo.getInputStream();
//
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentType("image/jpeg");
//
//
//            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3Filename, inputStream, metadata);
//            s3Client.putObject(putObjectRequest);
//            return "https://" + bucketName + ".s3.amazonaws.com/"  +  s3Filename;
//
//        } catch (Exception e) {
//            log.info(e.getMessage());
//            throw new NoActionException("Unable to upload image to s3 bucket" + e.getMessage());
//        }
//    }
    public String saveImageToS3(MultipartFile photo, String folder) {
        String s3Filename = folder+ "/" + (new Date().getTime()+ "_" + photo.getOriginalFilename());

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsS3AccessKey, awsS3SecretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.AP_SOUTHEAST_1)
                .build();

        List<PartETag> partETags = new ArrayList<>();
        String uploadId = null;

        try {
            // Bắt đầu quá trình tải lên đa phần
            InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(bucketName, s3Filename);
            uploadId = s3Client.initiateMultipartUpload(initRequest).getUploadId();

            // Chia tệp thành các phần và tải lên
            final int partSize = 5 * 1024 * 1024; // Kích thước phần 5MB
            long fileSize = photo.getSize();
            int partCount = (int) Math.ceil((double) fileSize / partSize);

            for (int i = 0; i < partCount; i++) {
                long startPos = i * partSize;
                long size = Math.min(partSize, fileSize - startPos);
                byte[] buffer = new byte[(int) size];
                InputStream inputStream = photo.getInputStream();
                inputStream.skip(startPos);
                inputStream.read(buffer, 0, (int) size);

                // Tải lên từng phần
                UploadPartRequest uploadRequest = new UploadPartRequest()
                        .withBucketName(bucketName)
                        .withKey(s3Filename)
                        .withUploadId(uploadId)
                        .withPartNumber(i + 1)
                        .withInputStream(new ByteArrayInputStream(buffer))
                        .withPartSize(size);
                partETags.add(s3Client.uploadPart(uploadRequest).getPartETag());
            }

            // Hoàn thành tải lên
            CompleteMultipartUploadRequest completeRequest = new CompleteMultipartUploadRequest(bucketName, s3Filename, uploadId, partETags);
            s3Client.completeMultipartUpload(completeRequest);

            return "https://" + bucketName + ".s3.amazonaws.com/" + s3Filename;

        } catch (Exception e) {
            if (uploadId != null) {
                // Hủy bỏ quá trình tải lên nếu có lỗi
                s3Client.abortMultipartUpload(new AbortMultipartUploadRequest(bucketName, s3Filename, uploadId));
            }
            log.info(e.getMessage());
            throw new NoActionException("Unable to upload image to s3 bucket: " + e.getMessage());
        }
    }
}


















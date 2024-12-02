package org.hcv.chodoido_ute_service.service.Implement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hcv.chodoido_ute_service.service.AwsS3Service;
import org.hcv.chodoido_ute_service.service.Interface.IUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UploadServiceService implements IUploadService {
    AwsS3Service awsS3Service;

    @Override
    public List<String> uploadImage(MultipartFile[] files) {
        String folder = "images";
        List<String> result = new ArrayList<>();
        for(MultipartFile file : files){
            result.add(awsS3Service.saveImageToS3(file, folder));
        }
        return result;
    }

    @Override
    public List<String> uploadVideo(MultipartFile[] files) {
        String folder = "videos";
        List<String> result = new ArrayList<>();
        for(MultipartFile file : files){
            result.add(awsS3Service.saveImageToS3(file, folder));
        }
        return result;
    }
}

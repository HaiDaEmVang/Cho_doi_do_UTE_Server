package org.hcv.chodoido_ute_service.service.Interface;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUploadService {
    List<String> uploadImage(MultipartFile[] file);

    List<String> uploadVideo(MultipartFile[] file);
}

package com.eventshub.services.impl;

import com.cloudinary.Cloudinary;
import com.eventshub.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

//@Service
//@RequiredArgsConstructor
//public class FileUploadServiceImpl implements FileUploadService {
//    private final Cloudinary cloudinary;
//    @Override
//    public String uploadFile(MultipartFile multipartFile) throws IOException {
//        return cloudinary.uploader()
//                .upload(multipartFile.getBytes(), Map.of("public_id", multipartFile.getOriginalFilename()))
//                .get("url")
//                .toString();
//    }
//}

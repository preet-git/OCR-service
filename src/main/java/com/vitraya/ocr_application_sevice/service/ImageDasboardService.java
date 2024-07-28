package com.vitraya.ocr_application_sevice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface ImageDasboardService {

    public void uploadImageData(MultipartFile image, Long userId) throws IOException;
}

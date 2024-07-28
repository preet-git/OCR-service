package com.vitraya.ocr_application_sevice.controller;

import com.vitraya.ocr_application_sevice.service.ImageDasboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/dashboard/")
@Slf4j
public class UserImagesDashboardController {

    @Autowired
    private ImageDasboardService imageDasboardService;

    @RequestMapping(method = RequestMethod.POST, path = "uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile image,
                                              @RequestParam("userId") Long userId) throws IOException {
        log.info("inside Controller{}", userId);
        imageDasboardService.uploadImageData(image,userId);
        return new ResponseEntity<>("Image Succesfully uploaded !", HttpStatus.OK);
    }
}

package com.vitraya.ocr_application_sevice.serviceImpl;

import com.vitraya.ocr_application_sevice.dto.ImageDetailsDto;
import com.vitraya.ocr_application_sevice.model.ImageDataEntity;
import com.vitraya.ocr_application_sevice.model.UserEntity;
import com.vitraya.ocr_application_sevice.repository.ImageDataRepository;
import com.vitraya.ocr_application_sevice.repository.UserRepository;
import com.vitraya.ocr_application_sevice.service.ImageDasboardService;
import com.vitraya.ocr_application_sevice.utilities.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
@Slf4j
public class ImageDashboardServiceImpl implements ImageDasboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageDataRepository imageDataRepository;
    @Override
    public void uploadImageData(MultipartFile image, Long userId) throws IOException {
        log.info("inside Service",userId);
        ImageDetailsDto imageDetailsDto = new ImageDetailsDto();
        imageDetailsDto.setImageName(image.getOriginalFilename());
        imageDetailsDto.setImageType(image.getContentType());
        imageDetailsDto.setImage(ImageUtil.convertImageToBase64(image));
        String imageToText = ImageUtil.extractTextFromImageTess(image);
        imageDetailsDto.setExtractedText(imageToText);
        imageDetailsDto.setBoldTextInImage(ImageUtil.extractedArrayOfBoldText(image));
        UserEntity userUploading = userRepository.findById(userId).orElseThrow(()->
                new UsernameNotFoundException("Unable to find user with given ID: "+userId));
        imageDetailsDto.setUser(userUploading);

        imageDataRepository.save(imageDataDtoToEntity(imageDetailsDto));
    }

    private ImageDataEntity imageDataDtoToEntity(ImageDetailsDto imageDto) {
        ImageDataEntity imageData = new ImageDataEntity();
        imageData.setImageName(imageDto.getImageName());
        imageData.setType(imageDto.getImageType());
        imageData.setImage(imageData.getImage());
        imageData.setBoldTextInImage(imageDto.getBoldTextInImage());
        imageData.setExtractedText(imageDto.getExtractedText());
        imageData.setUser(imageDto.getUser());
        imageData.setCreatedOn(new Date(System.currentTimeMillis()));

        return imageData;
    }
}

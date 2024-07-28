package com.vitraya.ocr_application_sevice.dto;

import com.vitraya.ocr_application_sevice.model.UserEntity;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ImageDetailsDto implements Serializable {
    private static final long SerialVersionUID = 1L;
    private long id;

    private String imageName;

    private String imageType;

    private String[] boldTextInImage;

    private String extractedText;

    private String image;

    private UserEntity user;

}

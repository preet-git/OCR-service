package com.vitraya.ocr_application_sevice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "image_dashboard")
@Data
public class ImageDataEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_id")
    private String imageName;

    @Column(name = "image_type")
    private String type;

    @Column(name = "image_text")
    private String extractedText;

    @Lob
    @Column(name = "bold_text")
    private String[] boldTextInImage;

    @Column(name = "image_valueB64")
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column
    private Date createdOn;
}

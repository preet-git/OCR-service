package com.vitraya.ocr_application_sevice.repository;

import com.vitraya.ocr_application_sevice.model.ImageDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ImageDataRepository extends JpaRepository<ImageDataEntity, Long> {

}

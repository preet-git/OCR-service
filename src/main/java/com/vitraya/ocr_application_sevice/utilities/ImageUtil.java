package com.vitraya.ocr_application_sevice.utilities;


import com.asprise.ocr.Ocr;
import net.sourceforge.tess4j.*;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.ResultIterator;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.swing.text.Document;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

public class ImageUtil {

    public static String convertImageToBase64(MultipartFile image) throws IOException {
        byte[] imageByteArray = image.getBytes();
        return Base64.getEncoder().encodeToString(imageByteArray);

    }
    public static void decodeBase64ToImage(String base64Image, String outputPath) throws IOException {
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            fos.write(imageBytes);
        }catch (IOException i){
            throw new IOException(i.getMessage());
        }
    }

    public static String extractTextFromImage(MultipartFile image) {
        Ocr.setUp();
        try{
            Ocr ocr = new Ocr();
            ocr.startEngine("eng",Ocr.SPEED_FAST);
            URL imageURL = image.getResource().getURL();
            String extractedText = ocr.recognize(new URL[]{imageURL},Ocr.RECOGNIZE_TYPE_TEXT,Ocr.RECOGNIZE_TYPE_TEXT);
            ocr.stopEngine();
            return extractedText;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractTextFromImageTess(MultipartFile image){
        ITesseract tesseract = new Tesseract();

        try{
            tesseract.setDatapath("/Users/preetahlawat/Downloads/Tess4J/tessdata");
            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
            String extractedText = tesseract.doOCR(bufferedImage);
            return extractedText;
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] extractedArrayOfBoldText(MultipartFile imageText) {


        return null;
    }
}

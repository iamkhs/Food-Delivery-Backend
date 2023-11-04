package com.iamkhs.fooddelivery.service.impl;

import com.cloudinary.Cloudinary;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iamkhs.fooddelivery.exceptions.InvalidImageTypeException;
import com.iamkhs.fooddelivery.service.CloudinaryImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryImgServiceImpl implements CloudinaryImgService {

    private final Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file) {
        if (isImageValid(file)) {
            try {
                Map<?, ?> upload = cloudinary.uploader().upload(file.getBytes(), Map.of());
                ObjectMapper objectMapper = new ObjectMapper();
                String responseString = objectMapper.writeValueAsString(upload);
                JsonNode jsonNode = objectMapper.readTree(responseString);

                String url = jsonNode.get("secure_url").toString();
                StringBuilder sb = new StringBuilder(url);
                sb.deleteCharAt(0);
                sb.deleteCharAt(sb.length() - 1);
                return sb.toString();

            } catch (IOException e) {
                throw new RuntimeException("Image Upload field! " + e.getMessage() + ", " + e.getCause());
            }
        }
        throw new InvalidImageTypeException("Image not valid!");
    }

     private boolean isImageValid(MultipartFile file){
        if (file.getContentType() != null && file.getOriginalFilename() != null){
            String fileOriginalFilename = file.getOriginalFilename();
            return fileOriginalFilename.endsWith(".jpg") ||
                    fileOriginalFilename.endsWith(".jpeg") ||
                    fileOriginalFilename.endsWith(".png");
        }
        return false;
    }
}

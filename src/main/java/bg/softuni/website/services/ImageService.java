package bg.softuni.website.services;

import bg.softuni.website.models.entities.Image;
import bg.softuni.website.repositories.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ImageService {
    
    private ImageRepository imageRepository;
    
    @Value("${image.upload.formNewTreatmentDir}")
    private String formNewTreatmentUploadDir;
    
    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    
    
    public Image saveImage(MultipartFile file, String formName) throws IOException {
        String uploadDir;
        switch (formName) {
            case "formNewTreatment":
                uploadDir = formNewTreatmentUploadDir;
                break;
            default:
                throw new IllegalArgumentException("!!!   You Really Broke It   !!!");
        }
        
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.write(filePath, file.getBytes());
        
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setUrl("/images/treatments/" + file.getOriginalFilename());
        
        //  TODO set the user from security
//        image.setAddedByUserEntity(userSession.getUser());
        this.imageRepository.saveAndFlush(image);
        
        return image;
    }
    
    public void deleteImage(Image image) throws IOException {
        this.imageRepository.deleteById(image.getId());
        String name = image.getName();
        Path filePath = Path.of(formNewTreatmentUploadDir, name);
        Files.delete(filePath);
    }
    
   
    public Image updateImage(MultipartFile newImage, Image oldImage, String formName) throws IOException {
        
        try {
            if (newImage == null || newImage.isEmpty()) {
                throw new IOException();
            }
            deleteImage(oldImage);
            return saveImage(newImage, formName);
        } catch (Exception e) {
            return oldImage;
        }
    }
}

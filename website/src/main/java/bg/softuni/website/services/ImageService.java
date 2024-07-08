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
    private FileSystemHelper fileSystemHelper;
    
    @Value("${image.upload.formNewTreatmentDir}")
    private String formNewTreatmentUploadDir;
    
    @Autowired
    public ImageService(ImageRepository imageRepository, FileSystemHelper fileSystemHelper) {
        this.imageRepository = imageRepository;
        this.fileSystemHelper = fileSystemHelper;
    }
    
    @Transactional
    public Image saveImage(MultipartFile file, String formName) throws IOException {
        String uploadDir;
        switch (formName) {
            case "formNewTreatment":
                uploadDir = formNewTreatmentUploadDir;
                break;
            default:
                throw new IllegalArgumentException("!!!   You Really Broke It   !!!");
        }
        
        this.fileSystemHelper.createDirectoryIfNotExists(uploadDir);
        
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
    
    @Transactional
    public Image updateImage(MultipartFile newImage, String formName, Optional<Image> oldImage) throws IOException {
        
        try {
            if (newImage == null || newImage.isEmpty()) {
                throw new IOException();
            }
            
            String uploadDir;
            switch (formName) {
                case "formNewTreatment":
                    uploadDir = formNewTreatmentUploadDir;
                    break;
                default:
                    throw new IllegalArgumentException("!!!   You Really Broke It   !!!");
            }
            
            String fileName = newImage.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            fileSystemHelper.saveFile(filePath, newImage);
            
            Image image = oldImage.orElseThrow(() -> new IllegalArgumentException("Old image not present"));
            Path oldFlePath = Path.of(formNewTreatmentUploadDir, image.getName());
            fileSystemHelper.deleteFile(oldFlePath);
            
            image.setName(newImage.getOriginalFilename());
            image.setUrl("/images/treatments/" + newImage.getOriginalFilename());
            
            //  TODO set the user from security
//        image.setAddedByUserEntity(userSession.getUser());
            
            this.imageRepository.saveAndFlush(image);
            return image;
            
        } catch (Exception e) {
            return oldImage.get();
        }
    }
    
    @Transactional
    public void deleteImage(Image image) throws IOException {
        this.imageRepository.delete(image);
        Path filePath = Path.of(formNewTreatmentUploadDir, image.getName());
        fileSystemHelper.deleteFile(filePath);
    }
    
}

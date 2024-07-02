package bg.softuni.website.services;

import bg.softuni.website.models.entities.Image;
import bg.softuni.website.repositories.ImageRepository;
import bg.softuni.website.sessions.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {
    
    private ImageRepository imageRepository;
    private UserSession userSession;
    
    @Value("${image.upload.formNewTreatmentDir}")
    private String formNewTreatmentUploadDir;
    
    @Autowired
    public ImageService(ImageRepository imageRepository, UserSession userSession) {
        this.imageRepository = imageRepository;
        this.userSession = userSession;
    }
    
    
    public Image saveImage(MultipartFile file, String formName) throws IOException {
        String uploadDir;
        switch (formName) {
            case "formNewTreatment":
                uploadDir = formNewTreatmentUploadDir;
                break;
            default: throw new IllegalArgumentException("!!!   You Really Broke It   !!!");
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
        image.setAddedByUser(userSession.getUser());
        this.imageRepository.saveAndFlush(image);
        
        return image;
    }
}

package bg.softuni.website.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileSystemHelper {
    
    public void createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    
    public void saveFile(Path path, MultipartFile file) throws IOException {
        Files.write(path, file.getBytes());
    }
    
    public void deleteFile(Path path) throws IOException {
        Files.delete(path);
    }
}

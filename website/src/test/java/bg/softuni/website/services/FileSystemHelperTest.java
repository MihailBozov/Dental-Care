package bg.softuni.website.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemHelperTest {
    
    private FileSystemHelper fileSystemHelper;
    
    @BeforeEach
    void setUp() {
        fileSystemHelper = new FileSystemHelper();
    }
    
    @Test
    void createDirectoryIfNotExists(@TempDir Path tempDir) {
        String directoryPath = tempDir.resolve("newDirectory").toString();
        
        fileSystemHelper.createDirectoryIfNotExists(directoryPath);
        
        File directory = new File(directoryPath);
        assertTrue(directory.exists());
        assertTrue(directory.isDirectory());
    }
    
    @Test
    void saveFile(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("testFile.txt");
        MultipartFile multipartFile = new MockMultipartFile("file", "testFile.txt", "text/plain", "Hello, World!".getBytes());
        
        fileSystemHelper.saveFile(filePath, multipartFile);
        
        assertTrue(Files.exists(filePath));
        assertEquals("Hello, World!", Files.readString(filePath));
    }
    
    @Test
    void deleteFile(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("testFile.txt");
        Files.createFile(filePath);
        
        assertTrue(Files.exists(filePath));
        
        fileSystemHelper.deleteFile(filePath);
        
        assertFalse(Files.exists(filePath));
    }
}
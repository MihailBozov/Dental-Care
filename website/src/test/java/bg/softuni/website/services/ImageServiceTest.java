package bg.softuni.website.services;

import bg.softuni.website.models.entities.Image;
import bg.softuni.website.repositories.ImageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {
    
    @Mock
    private ImageRepository imageRepository;
    
    @Mock
    private FileSystemHelper fileSystemHelper;
    
    private String formNewTreatmentUploadDir;
    
    @InjectMocks
    private ImageService imageService;
    
    @BeforeEach
    void setUp() throws IllegalAccessException, NoSuchFieldException {
        imageService = new ImageService(imageRepository, fileSystemHelper);
        formNewTreatmentUploadDir = "uploads/treatments";
        
        Field field = ImageService.class.getDeclaredField("formNewTreatmentUploadDir");
        field.setAccessible(true);
        field.set(imageService, formNewTreatmentUploadDir);
        
    }
    
    @Test
    void saveImage() throws IOException {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test".getBytes());
        
        doNothing().when(fileSystemHelper)
                .createDirectoryIfNotExists(anyString());
        
        Image image = imageService.saveImage(multipartFile, "formNewTreatment");
        
        Assertions.assertNotNull(image);
        Assertions.assertEquals("test.jpg", image.getName());
        Assertions.assertEquals("/images/treatments/test.jpg", image.getUrl());
        verify(imageRepository, times(1))
                .saveAndFlush(image);
    }
    
    @Test
    void saveImageWithInvalidFormNameShouldThrowIllegalArgumentException() {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.jpg", "image/jpg", "test".getBytes());
        
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            imageService.saveImage(multipartFile, "invalidFormName");
        });
        Assertions.assertEquals("!!!   You Really Broke It   !!!", exception.getMessage());
    }
    
    @Test
    void updateImage() throws IOException {
        MockMultipartFile multipartFile = new MockMultipartFile("newImage", "test.jpg", "image/jpg", "test".getBytes());
        Image oldImage = new Image();
        oldImage.setName("oldImage.jpg");
        oldImage.setUrl("/images/treatments/oldTest.jpg");
        Optional<Image> oldImageOpt = Optional.of(oldImage);
        
        
        doNothing().when(fileSystemHelper).saveFile(any(Path.class), any(MultipartFile.class));
        doNothing().when(fileSystemHelper).deleteFile(any(Path.class));
        
        Image image = imageService.updateImage(multipartFile, "formNewTreatment", oldImageOpt);
        
        Assertions.assertNotNull(image);
        Assertions.assertEquals("test.jpg", image.getName());
        Assertions.assertEquals("/images/treatments/test.jpg", image.getUrl());
        
        ArgumentCaptor<Path> pathCapture = ArgumentCaptor.forClass(Path.class);
        ArgumentCaptor<MultipartFile> fileCaptor = ArgumentCaptor.forClass(MultipartFile.class);
        
        verify(fileSystemHelper, times(1)).saveFile(pathCapture.capture(),fileCaptor.capture());
        
        Path capturedPath = pathCapture.getValue();
        Path expectedPath = Paths.get(formNewTreatmentUploadDir, image.getName());
        Assertions.assertEquals(expectedPath, Path.of(formNewTreatmentUploadDir, image.getName()));
        Assertions.assertEquals(expectedPath, capturedPath);
        Assertions.assertEquals(multipartFile, fileCaptor.getValue());
        verify(fileSystemHelper, times(1)).deleteFile(any(Path.class));
    }
    
    @Test
    void updateImageWhenTheNewImageIsNull() throws IOException {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.jpg", "image/jpg", "test".getBytes());
        Image oldImage = new Image();
        oldImage.setName("oldImage.jpg");
        oldImage.setUrl("/images/treatments/oldTest.jpg");
        Optional<Image> oldImageOpt = Optional.of(oldImage);
        
        Image image = imageService.updateImage(null, "formNewTreatment", oldImageOpt);
        
        Assertions.assertNotNull(image);
        Assertions.assertEquals("oldImage.jpg", image.getName());
        Assertions.assertEquals("/images/treatments/oldTest.jpg", image.getUrl());
        verify(imageRepository, never()).saveAndFlush(any(Image.class));
    }
    
    @Test
    void updateImageWithInvalidFormName() throws IOException {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.jpg", "image/jpg", "test".getBytes());
        Image oldImage = new Image();
        oldImage.setName("oldImage.jpg");
        oldImage.setUrl("/images/treatments/oldTest.jpg");
        Optional<Image> oldImageOpt = Optional.of(oldImage);
        
        Image image = imageService.updateImage(multipartFile, "invalidFormName", oldImageOpt);
        
        Assertions.assertNotNull(image);
        Assertions.assertEquals("oldImage.jpg", image.getName());
        Assertions.assertEquals("/images/treatments/oldTest.jpg", image.getUrl());
        verify(imageRepository, never())
                .saveAndFlush(any(Image.class));
    }
    
    @Test
    void deleteImage() throws IOException {
        Image image = new Image();
        image.setName("imageTest.jpg");
        image.setUrl("/images/treatments/imageTest.jpg");
        Path path = Path.of(formNewTreatmentUploadDir, image.getName());

        doNothing().when(imageRepository).delete(image);
        doNothing().when(fileSystemHelper).deleteFile(path);
        
        imageService.deleteImage(image);
        
        verify(imageRepository, times(1)).delete(image);
        verify(fileSystemHelper, times(1)).deleteFile(path);
    }
}
package bg.softuni.website.services;

import bg.softuni.website.models.dtos.TestimonialDto;
import bg.softuni.website.models.entities.Image;
import bg.softuni.website.models.entities.Testimonial;
import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.repositories.ImageRepository;
import bg.softuni.website.repositories.TestimonialsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestimonialServiceTest {
    
    @Mock
    private TestimonialsRepository testimonialsRepository;
    
    @Mock
    private ImageRepository imageRepository;
    
    @InjectMocks
    private TestimonialService testimonialService;
    
    private UserEntity userEntity;
    private Image image1;
    private Image image2;
    private Testimonial testimonial;
    
    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
        userEntity.setFirstName("Sand");
        
        image1 = new Image();
        image1.setUrl("image1Url");
        
        image2 = new Image();
        image2.setUrl("image2Url");
        
        Image userImage = new Image();
        userImage.setUrl("userImageUrl");
        userEntity.setImage(userImage);
        
        testimonial = new Testimonial();
        testimonial.setContent("Great service!");
        testimonial.setCountStars(5);
        userEntity.setTestimonial(testimonial);
    }
    
    @Test
    void getAllTestimonials() {
        
        when(testimonialsRepository.findAllUsersWithTestimonials())
                .thenReturn(List.of(userEntity));
        when(imageRepository.findAllQuoteImagesOrdered())
                .thenReturn(List.of(image1, image2));
        
        List<TestimonialDto> testimonials = testimonialService.getAllTestimonials();
        Assertions.assertEquals(1, testimonials.size());
        
        TestimonialDto testimonialDto = testimonials.getFirst();
        
        Assertions.assertEquals("userImageUrl", testimonialDto.getAuthorImageUrl());
        Assertions.assertEquals("Sand", testimonialDto.getAuthorFirstName());
        Assertions.assertEquals(5, testimonialDto.getStars().length);
    }
    
    @Test
    void deleteTestimonial() {
        long id = 1L;
        doNothing().when(testimonialsRepository).deleteById(id);
        testimonialService.deleteTestimonial(id);
        verify(testimonialsRepository, times(1))
                .deleteById(id);
    }
    
}
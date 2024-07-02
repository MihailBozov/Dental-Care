package bg.softuni.website.services;

import bg.softuni.website.models.dtos.TestimonialDto;
import bg.softuni.website.models.entities.Image;
import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.repositories.ImageRepository;
import bg.softuni.website.repositories.TestimonialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestimonialService {
    private TestimonialsRepository testimonialsRepository;
    private ImageRepository imageRepository;
    
    @Autowired
    public TestimonialService(TestimonialsRepository testimonialsRepository, ImageRepository imageRepository) {
        this.testimonialsRepository = testimonialsRepository;
        this.imageRepository = imageRepository;
    }
    
    
    public List<TestimonialDto> getAllTestimonials() {
        List<UserEntity> userEntities = this.testimonialsRepository.findAllUsersWithTestimonials();
        List<Image> icons = this.imageRepository.findAllQuoteImagesOrdered();
        List<TestimonialDto> testimonialDtos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            TestimonialDto testimonialDto = new TestimonialDto();
            testimonialDto.setAuthorFirstName(userEntity.getFirstName());
            testimonialDto.setAuthorImageUrl(userEntity.getImage().getUrl());
            testimonialDto.setContent(userEntity.getTestimonial().getContent());
            
            testimonialDto.setStars(new int[userEntity.getTestimonial().getCountStars()]);
            testimonialDto.setQuoteStartImageUrl(icons.get(0).getUrl());
            testimonialDto.setQuoteEndImageUrl(icons.get(1).getUrl());
            testimonialDtos.add(testimonialDto);
        }
        return testimonialDtos;
    }
}

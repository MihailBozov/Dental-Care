package bg.softuni.website.services;

import bg.softuni.website.models.dtos.TestimonialDto;
import bg.softuni.website.models.entities.Image;
import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.repositories.ImageRepository;
import bg.softuni.website.repositories.TestimonialsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestimonialService {
   private Logger logger = LoggerFactory.getLogger(TestimonialService.class);
    private TestimonialsRepository testimonialsRepository;
    private ImageRepository imageRepository;
    
    @Autowired
    public TestimonialService(TestimonialsRepository testimonialsRepository, ImageRepository imageRepository) {
        this.testimonialsRepository = testimonialsRepository;
        this.imageRepository = imageRepository;
    }
    
    @Cacheable("testimonials")
    public List<TestimonialDto> getAllTestimonials() {
        logger.warn("getAllTestimonials() is annotated with @Cacheable and is executed !");
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
    

    @CacheEvict(value = "testimonials", allEntries = true)
    public void deleteTestimonial(Long id) {
        this.testimonialsRepository.deleteById(id);
    }
}
